---
markdown:
  image_dir: ../assets
  path: ../TDD.md
  ignore_from_front_matter: true
  absolute_image_path: false
---

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
+ [ ] DollarとFrancの重複
+ [x] ~~equalsの一般化~~
+ [ ] timesの一般化
+ [x] ~~FrancとDollarを比較する~~
+ [ ] **通貨の概念**
+ [ ] testFrancMultiplicationを削除する？


## コアモデル
### クラス図
```puml
class Dollar {
  +Dollar(amount:int) :Dollar
  ~times(multiplier:int) :Money
}
class Franc {
  +Franc(amount:int) :Dollar
  ~times(multiplier:int) :Money
}
abstract Money {
  #amount:int
  #currency  
  +equals(object:Object) :boolean  
  {abstract} times(multiplier:int)
  ~currency() :String
  {static} dollar(amount:int) :Money
  {static} franc(amount:int) :Money  
}
Money <|-- Dollar
Money <|-- Franc
```
### シーケンス図

## コード
`MoneyTest.java`
@import "../../src/test/java/tdd/money/MoneyTest.java"

`Dollar.java`
@import "../../src/main/java/tdd/money/Dollar.java"

`Franc.java`
@import "../../src/main/java/tdd/money/Franc.java"

`Money.java`
@import "../../src/main/java/tdd/money/Money.java"

## 振り返り
