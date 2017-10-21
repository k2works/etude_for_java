---
markdown:
  image_dir: /docs/assets
  path: /docs/TDD.md
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
|IBM       |1000|25 USD  |25000|
|Novartis  |400 |100 CHF |40000|
|          |    |総計 |65000|
  
加えて、為替レートも規定しなければならない
  
|換算元|換算先|レート|
|:----|:----|:----|
|CHF|USD|1.5|
  
+ 通貨の異なる２つの金額を足し、通貨間の為替レートに基づいて換算された金額を得る。
+ 金額（通貨単位あたりの額）に数値（通貨単位数）を掛け、金額を得る。

## コアモデル
```puml
class Money {
  # amount
  # currency
  times()
  currency()
  + equals()
  + dollar()
  + franc()

}
```
  
## TODOリスト
  
+ [ ] \$5 + 10CHF = \$10 (レートが2:1の場合)
+ [x] ~~\$5 * 2 = \$10~~
+ [x] ~~amountをprivateにする~~
+ [x] ~~Dollarの副作用をどうする？~~
+ [ ] Moneyの丸め処理をどうする？
+ [x] ~~equals()~~
+ [ ] hashCode()
+ [ ] nullとの等価性比較
+ [ ] 他のオブジェクトとの等価性比較
+ [x] ~~5CHF * 2 = 10 CHF~~
+ [x] ~~DollarとFrancの重複~~
+ [x] ~~equalsの一般化~~
+ [x] ~~timesの一般化~~
+ [x] ~~FrancとDollarを比較する~~
+ [x] ~~通貨の概念~~
+ [x] ~~testFrancMultiplicationを削除する？~~