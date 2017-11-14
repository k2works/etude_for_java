  
  
# リファクタリング
  
  
## 要求
  
顧客が借りたビデオのレンタル料金を計算して計算書を印刷する。

![](./assets/f657651786015ad6423a3b38afc56ad70.png?0.20124907986267182)  
  
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
+ [ ] **statementメソッドの分割、再配置**
  
### クラス図
  

![](./assets/f657651786015ad6423a3b38afc56ad71.png?0.6402748079566709)  
  
### シーケンス図
  
statement(計算書生成)メソッドのシーケンス図

![](./assets/f657651786015ad6423a3b38afc56ad72.png?0.33480173205593045)  
## 実装
  
### `CustomerTest.java`
  
```java
package refactoring.videorental;
  
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class CustomerTest {
    @Test
    public void rentalRegularMovieForAWeek() {
        Movie movie = new Movie("Attack of the Killer Tomatoes!", Movie.REGULAR);
        Rental rental = new Rental(movie,7);
        Customer customer = new Customer("Mike");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Mike\n" +
                "\tAttack of the Killer Tomatoes!\t9.5\n" +
                "Amount owed is 9.5\n" +
                "You earned 1 frequent renter points",result);
    }
    @Test
    public void rentalChildrensMovieForTwoWeeks() {
        Movie movie = new Movie("PUELLA MAGI MADOKA MAGICA", Movie.CHILDRENS);
        Rental rental = new Rental(movie,14);
        Customer customer = new Customer("John");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for John\n" +
                "\tPUELLA MAGI MADOKA MAGICA\t16.5\n" +
                "Amount owed is 16.5\n" +
                "You earned 1 frequent renter points",result);
    }
    @Test
    public void rentalNewReleaseMovieForADay() {
        Movie movie = new Movie("The Return of the Living Dead", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,1);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points",result);
    }
    @Test
    public void rentalNewReleaseMovieTwoDays() {
        Movie movie = new Movie("The Return of the Living Dead Part II", Movie.NEW_RELEASE);
        Rental rental = new Rental(movie,2);
        Customer customer = new Customer("Nancy");
        customer.addRental(rental);
        String result = customer.statement();
        assertEquals("Rental Record for Nancy\n" +
                "\tThe Return of the Living Dead Part II\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points",result);
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
    private int _priceCode;
  
    public Movie(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }
  
    public int getPriceCode() {
        return _priceCode;
    }
  
    public void setPriceCode(int arg) {
        _priceCode = arg;
    }
  
    public String getTitle() {
        return _title;
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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while(rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();
  
            thisAmount = amountFor(each);
  
            //レンタルポイントを加算
            frequentRenterPoints ++;
            //新作を二日以上借りた場合はボーナスポイント
            if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1) frequentRenterPoints ++;
  
            //この貸し出しに関する数値の表示
            result += "\t" + each.getMovie().getTitle() + "\t" +
                    String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //フッタ部分の追加
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }
  
    private double amountFor(Rental aRental) {
        double result = 0;
        switch (aRental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (aRental.getDaysRented() > 2)
                    result += (aRental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                result += aRental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                if (aRental.getDaysRented() > 3)
                    result += (aRental.getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }
}
  
```  
  