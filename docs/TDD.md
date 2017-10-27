  
  
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
+ [ ] amountをprivateにする
+ [x] ~~Dollarの副作用どうする？~~
+ [ ] Moneyの丸め処理をどうする？
  
  
## コアモデル
  
### クラス図
  

![](./assets/0285dfa24ee25b18e00bb369b57da6820.png?0.6029955648547065)  
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
        Dollar product = five.times(2);
        assertEquals(10, product.amount);
        product = five.times(3);
        assertEquals(15, product.amount);
    }
}
  
```  
  
`Dollar.java`
```java
package tdd.money;
  
class Dollar {
    int amount;
    Dollar(int amount){
        this.amount = amount;
    }
    Dollar times(int multiplier) {
        return new Dollar(amount * multiplier);
    }
}
  
```  
  
## 振り返り
  
+ 設計の問題点（今回は副作用）をテストコードに写し取り、その問題点のせいでテストが失敗するのを確認した。
+ 空実装でさっさとコンパイルを通した。
+ 正しいと思える実装をすぐに行い、テストを通した。
  