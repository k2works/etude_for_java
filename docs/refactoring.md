  
  
# リファクタリング
  
  
## 要求
  
顧客が借りたビデオのレンタル料金を計算して計算書を印刷する。

![](./assets/f657651786015ad6423a3b38afc56ad70.png?0.8078136832148044)  
  
## 仕様
  
+ ビデオレンタルの料金を計算して計算書を印刷するプログラム
+ システムにはどの映画を何日間借りるかが入力される。
+ 貸出の日数によって料金が計算され、映画の分類が判定される。
+ 映画の分類は３つある。一般向け、子供向け、新作。
+ レンタルポイントも印刷される。新作かどうかによってポイント計算の仕方が異なる。
  
## 設計
  
### TODO
  
+ [ ] 一般向けビデオのレンタルテスト作成
+ [ ] 子供向けビデオのレンタルテスト作成
+ [ ] 新作ビデオのレンタルテスト作成
  
### クラス図
  

![](./assets/f657651786015ad6423a3b38afc56ad71.png?0.41685961834612795)  
  
### シーケンス図
  
statement(計算書生成)メソッドのシーケンス図

![](./assets/f657651786015ad6423a3b38afc56ad72.png?0.7494876522918512)  
## 実装
  
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
  
            //一行ごとに金額を計算
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }
  
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
}
  
```  
  