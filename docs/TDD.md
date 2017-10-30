  
  
# テスト駆動開発
  
  
  
## 基本仕様
  
  
  
既存レポート
  
|銘柄|株数|価格|合計|
|:---- |:----:|----:|----:|
|IBM |1000|25  |25000|
|GE  |400 |100 |40000|
|    |    |総計 |65000|
  
レポートを多国籍通貨対応させるために、通貨の情報を加えなければならない。
  
|銘柄       |株数  |価格  |合計  |
|:----     |----:|----:|----:|
|IBM       |1000|25 USD  |25000 USD|
|Novartis  |400 |150 CHF |60000 CHF|
|          |    |総計 |65000 USD|
  
加えて、為替レートも規定しなければならない
  
|換算元|換算先|レート|
|:----|:----|:----|
|CHF|USD|1.5|
  
+ 通貨の異なる２つの金額を足し、通貨間の為替レートに基づいて換算された金額を得る。
+ 金額（通貨単位あたりの額）に数値（通貨単位数）を掛け、金額を得る。
  
## TODOリスト
  
  
+ [ ] \$5 + 10CHF = \$10 (レートが2:1の場合)
+ [x] ~~\$5 * 2 = \$10~~
+ [x] ~~amountをprivateにする~~
+ [x] ~~Dollarの副作用どうする？~~
+ [ ] Moneyの丸め処理をどうする？
+ [x] ~~equals()~~
+ [ ] hashCode()
+ [ ] nullとの等価性比較
+ [ ] 他のオブジェクトとの等価性比較
+ [x] ~~5CHF + 2 = 10CHF~~
+ [ ] **DollarとFrancの重複**
+ [x] ~~equalsの一般化~~
+ [ ] timesの一般化
+ [x] ~~FrancとDollarを比較する~~
+ [ ] 通貨の概念
  
  
## コアモデル
  
### クラス図
  

![](./assets/0285dfa24ee25b18e00bb369b57da6820.png?0.847827515977341)  
### シーケンス図
  
  
## コード
  
`MoneyTest.java`
```java
package tdd.money;
  
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class MoneyTest {
    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }
    @Test
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertTrue(new Franc(5).equals(new Franc(5)));
        assertFalse(new Franc(5).equals(new Franc(6)));
        assertFalse(new Franc(5).equals(Money.dollar(5)));
    }
    @Test
    public void testFrancMultiplication() {
        Franc five = new Franc(5);
        assertEquals(new Franc(10), five.times(2));
        assertEquals(new Franc(15), five.times(3));
    }
}
  
```  
  
`Dollar.java`
```java
package tdd.money;
  
class Dollar extends Money {
    Dollar(int amount){
        this.amount = amount;
    }
    Money times(int multiplier) {
        return new Dollar(amount * multiplier);
    }
}
  
```  
  
`Franc.java`
```java
package tdd.money;
  
class Franc extends Money {
    Franc(int amount) {
        this.amount = amount;
    }
    Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }
}
  
```  
  
`Money.java`
```java
package tdd.money;
  
abstract class Money {
    protected int amount;
    abstract Money times(int multiplier);
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && getClass().equals(money.getClass());
    }
    static Money dollar(int amount) {
        return new Dollar(amount);
    }
}
  
```  
  
## 振り返り
  
  