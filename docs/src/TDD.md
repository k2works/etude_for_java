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
+ [ ] **\$5 + \$5 = \$10**

## コアモデル
### クラス図
```puml
class Money {
  #amount:int
  #currency  
  +Money(amount:int,currency:String) :Money
  +equals(object:Object) :boolean  
  +toString() :String
  times(multiplier:int) :Money
  ~currency() :String
  ~plus(addend: Money) :Expression
  {static} dollar(amount:int) :Money
  {static} franc(amount:int) :Money  
}
interface Expression
class Bank {
  ~reduce(source: Money, to: String) :Money
}
Expression <|.. Money
Bank -> Money
```
### シーケンス図
```puml
Bank -> Money :reduce()
activate Money
  Money -> Money :dollar()
  Bank <-- Money
deactivate Money
```

## コード
`MoneyTest.java`
@import "../../src/test/java/tdd/money/MoneyTest.java"

`Money.java`
@import "../../src/main/java/tdd/money/Money.java"

`Expression.java`
@import "../../src/main/java/tdd/money/Expression.java"

`Bank.java`
@import "../../src/main/java/tdd/money/Bank.java"


## 振り返り
+ 大きいテスト（\$5 + 10CHF）を分割して、進み具合がわかる小さいテスト（\$5 + \$5）を作成した。
+ これから行う計算のためのメタファーについて深く考えた。
+ 前章で書いたテストを、新しいメタファーを使って書き直した。
+ テストがコンパイルできるところまで早足で進んだ。
+ テストを通した。
+ 本当の実装を導くためのリファクタリングを楽しみにしつつ、少し不安も感じている。
