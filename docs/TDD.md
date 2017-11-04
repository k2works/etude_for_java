  
  
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
+ [ ] **\$5 + \$5 = \$10**
  
## コアモデル
  
### クラス図
  

![](./assets/0285dfa24ee25b18e00bb369b57da6820.png?0.6540654020091159)  
### シーケンス図
  

![](./assets/0285dfa24ee25b18e00bb369b57da6821.png?0.47496058302864697)  
  
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
  
    Expression plus(Money addend) {
        return new Money(amount + addend.amount, currency);
    }
}
  
```  
  
`Expression.java`
```java
package tdd.money;
  
interface Expression {
}
  
```  
  
`Bank.java`
```java
package tdd.money;
  
class Bank {
    Money reduce(Expression source, String to) {
        return Money.dollar(10);
    }
}
  
```  
  
  
## 振り返り
  
+ 大きいテスト（\$5 + 10CHF）を分割して、進み具合がわかる小さいテスト（\$5 + \$5）を作成した。
+ これから行う計算のためのメタファーについて深く考えた。
+ 前章で書いたテストを、新しいメタファーを使って書き直した。
+ テストがコンパイルできるところまで早足で進んだ。
+ テストを通した。
+ 本当の実装を導くためのリファクタリングを楽しみにしつつ、少し不安も感じている。
  