  
  
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
  
  
## コアモデル
  
### クラス図
  

![](./assets/0285dfa24ee25b18e00bb369b57da6820.png?0.6735668731196542)  
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
        Dollar five = new Dollar(5);
        assertEquals(new Dollar(10), five.times(2));
        assertEquals(new Dollar(15), five.times(3));
    }
    @Test
    public void testEquality() {
        assertTrue(new Dollar(5).equals(new Dollar(5)));
        assertFalse(new Dollar(5).equals(new Dollar(6)));
    }
}
  
```  
  
`Dollar.java`
```java
package tdd.money;
  
class Dollar {
    private int amount;
    Dollar(int amount){
        this.amount = amount;
    }
    Dollar times(int multiplier) {
        return new Dollar(amount * multiplier);
    }
    public boolean equals(Object object) {
        Dollar dollar = (Dollar) object;
        return amount == dollar.amount;
    }
}
  
```  
  
## 振り返り
  
+ 作成したばかりの機能を使って、テストを改善した。
+ そもそも正しい検証できていないテストが２つあったら、もはやお手上げだと気づいた。
+ そのようなリスクを受け入れて先に進んだ。
+ テスト対象オブジェクトの新しい機能を使い、テストコードとプロダクトコードの間の結合度を下げた。
  