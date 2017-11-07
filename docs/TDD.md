  
  
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
+ [ ] \$5 + \$5 = \$10
+ [ ] \$5 + \$5がMoneyを返す
+ [x] ~~Bank.reduce(Money)~~
+ [ ] **Moneyを変換して換算を行う**
+ [ ] Reduce(Bank, String)
  
## コアモデル
  
### クラス図
  

![](./assets/0285dfa24ee25b18e00bb369b57da6820.png?0.2669775956128191)  
### シーケンス図
  

![](./assets/0285dfa24ee25b18e00bb369b57da6821.png?0.368992106655994)  
  
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
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }
    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }
    @Test
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }
    @Test
    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }
    @Test
    public void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }
    @Test
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }
    @Test
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }
}
  
```  
  
`Money.java`
```java
package tdd.money;
  
class Money implements Expression {
    protected int amount;
    protected String currency;
    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }
    Expression plus(Money addend) {
        return new Sum(this, addend);
    }
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }
    String currency() {
        return currency;
    }
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && currency().equals(money.currency());
    }
    public String toString() {
        return amount + " " + currency;
    }
    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }
    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }
}
  
```  
  
`Expression.java`
```java
package tdd.money;
  
interface Expression {
    Money reduce(Bank bank, String to);
}
  
```  
  
`Bank.java`
```java
package tdd.money;
  
class Bank {
    Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }
    void addRate(String chf, String usd, int i) {
    }
    int rate(String from, String to) {
        return (from.equals("CHF") && to.equals("USD")) ? 2 : 1;
    }
}
  
```  
  
`Sum.java`
```java
package tdd.money;
  
class Sum implements Expression {
    Money augend;
    Money addend;
    Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }
  
    public Money reduce(Bank bank, String to) {
        int amount = augend.amount + addend.amount;
        return new Money(amount, to);
    }
}
  
```  
  
  
## 振り返り
  
  