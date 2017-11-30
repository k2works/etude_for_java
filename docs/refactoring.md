  
  
# リファクタリング
  
  
## 要求
  
顧客が借りたビデオのレンタル料金を計算して計算書を印刷する。

![](../assets/f657651786015ad6423a3b38afc56ad70.png?0.04800589133984934)  
  
## 仕様
  
+ ビデオレンタルの料金を計算して計算書を印刷するプログラム
+ システムにはどの映画を何日間借りるかが入力される。
+ 貸出の日数によって料金が計算され、映画の分類が判定される。
+ 映画の分類は３つある。一般向け、子供向け、新作。
+ レンタルポイントも印刷される。新作かどうかによってポイント計算の仕方が異なる。
  
## 設計
  
### TODO
  
+ [x] ~~一般向けビデオのレンタルテスト作成~~
+ [x] ~~子供向けビデオのレンタルテスト作成~~
+ [x] ~~新作ビデオのレンタルテスト作成~~
+ [x] ~~statementメソッドの分割、再配置~~
+ [x] ~~amountForメソッドの移動~~
+ [x] ~~レンタルポイント計算部分の抽出~~
+ [x] ~~一時変数の削除~~
+ [x] ~~料金計算の条件文をポリモーフィズムに置き換える~~
+ [x] ~~最後は継承で~~~
  
### クラス図
  

![](./assets/f657651786015ad6423a3b38afc56ad71.png?0.021955248178963993)  
  
### シーケンス図
  
statement(計算書生成)メソッドのシーケンス図

![](./assets/f657651786015ad6423a3b38afc56ad72.png?0.1763762509002278)  
## 実装
  
### `CustomerTest.java`
  
```java
package refactoring.videorental;
  
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class CustomerTest {
    @Test
    public void rentalRegularMovieForAWeek() throws IllegalAccessException {
        Movie movie = new Movie("Attack of the Killer Tomatoes!", Movie.REGULAR);
        Rental rental = new Rental(movie,7);
        Customer customer = new Customer("Mike");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Mike\n" +
                "\tAttack of the Killer Tomatoes!\t9.5\n" +
                "Amount owed is 9.5\n" +
                "You earned 1 frequent renter points",result);
  
        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>Mike</EM></H1><P>\n" +
                "Attack of the Killer Tomatoes!: 9.5<BR>\n" +
                "<P>Amount owed <EM> 9.5</EM><P>\n" +
                "On this rental you earned <EM>1</EM> frequent renter points<P>",result);
    }
    @Test
    public void rentalChildrensMovieForTwoWeeks() throws IllegalAccessException {
        Movie movie = new Movie("PUELLA MAGI MADOKA MAGICA", Movie.CHILDRENS);
        Rental rental = new Rental(movie,14);
        Customer customer = new Customer("John");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for John\n" +
                "\tPUELLA MAGI MADOKA MAGICA\t18.0\n" +
                "Amount owed is 18.0\n" +
                "You earned 1 frequent renter points",result);
  
        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>John</EM></H1><P>\n" +
                "PUELLA MAGI MADOKA MAGICA: 18.0<BR>\n" +
                "<P>Amount owed <EM> 18.0</EM><P>\n" +
                "On this rental you earned <EM>1</EM> frequent renter points<P>",result);
    }
    @Test
    public void rentalNewReleaseMovieForADay() throws IllegalAccessException {
        Movie movie = new Movie("The Return of the Living Dead", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,1);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points",result);
  
        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>Nancy</EM></H1><P>\n" +
                "The Return of the Living Dead: 3.0<BR>\n" +
                "<P>Amount owed <EM> 3.0</EM><P>\n" +
                "On this rental you earned <EM>1</EM> frequent renter points<P>",result);
    }
    @Test
    public void rentalNewReleaseMovieTwoDays() throws IllegalAccessException {
        Movie movie = new Movie("The Return of the Living Dead Part II", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,2);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead Part II\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points",result);
  
        result = customer.htmlStatement();
        assertEquals("<H1>Rental Record for <EM>Nancy</EM></H1><P>\n" +
                "The Return of the Living Dead Part II: 6.0<BR>\n" +
                "<P>Amount owed <EM> 6.0</EM><P>\n" +
                "On this rental you earned <EM>2</EM> frequent renter points<P>",result);
    }
}
  
```  
### `Movie.java`
  
```java
package refactoring.videorental;
  
public class Movie {
  
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
  
    private String _title;
    private Price _price;
  
    public Movie(String title, int priceCode) throws IllegalAccessException {
        _title = title;
        setPriceCode(priceCode);
    }
  
    public int getPriceCode() {
        return _price.getPriceCode();
    }
  
    public void setPriceCode(int arg) throws IllegalAccessException {
        switch (arg) {
            case REGULAR:
                _price = new RegularPrice();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalAccessException("不正な料金コード");
        }
    }
  
    public String getTitle() {
        return _title;
    }
    double getCharge(int daysRented) {
        return _price.getCharge(daysRented);
    }
    int getFrequentRenterPoints(int daysRented) {
        return _price.getFrequentRenterPoints(daysRented);
    }
}
  
```  
### `Price.java`
  
```java
package refactoring.videorental;
  
abstract class Price {
    abstract int getPriceCode();
  
    abstract double getCharge(int daysRented);
  
    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
  
```  
### `ChildrensPrice.java`
  
```java
package refactoring.videorental;
  
class ChildrensPrice extends Price {
    int getPriceCode() {
        return Movie.CHILDRENS;
    }
  
    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}
  
```  
### `NewReleasePrice.java`
  
```java
package refactoring.videorental;
  
class NewReleasePrice extends Price {
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
  
    double getCharge(int daysRented) {
        return daysRented * 3;
    }
  
    int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}
  
```  
### `RegularPrice.java`
  
```java
package refactoring.videorental;
  
class RegularPrice extends Price {
    int getPriceCode() {
        return Movie.REGULAR;
    }
  
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
}
  
```  
### `Rental.java`
  
```java
package refactoring.videorental;
  
class Rental {
    private Movie _movie;
    private int _daysRented;
  
    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }
    public int getDaysRented() {
        return _daysRented;
    }
    public Movie getMovie() {
        return _movie;
    }
    double getCharge() {
        return _movie.getCharge(_daysRented);
    }
    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }
}
  
```  
### `Customer.java`
  
```java
package refactoring.videorental;
  
  
import java.util.Enumeration;
import java.util.Vector;
  
class Customer {
    private String _name;
    private Vector _rentals = new Vector();
  
    public Customer(String name) {
        _name = name;
    }
  
    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }
    public String getName() {
        return _name;
    }
    public String statement() {
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while(rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
  
            //この貸し出しに関する数値の表示
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(each.getCharge()) + "\n";
        }
        //フッタ部分の追加
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }
  
    public String htmlStatement() {
        Enumeration rentals = _rentals.elements();
        String result = "<H1>Rental Record for <EM>" + getName() + "</EM></H1><P>\n";
        while(rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
  
            //この貸し出しに関する数値の表示
            result += each.getMovie().getTitle() + ": " +
                    String.valueOf(each.getCharge()) + "<BR>\n";
        }
        //フッタ部分の追加
        result += "<P>Amount owed <EM> " + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";
        return result;
    }
  
    private double getTotalCharge() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }
  
    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
  
```  
  