---
markdown:
  image_dir: ../assets
  path: ../refactoring.md
  ignore_from_front_matter: true
  absolute_image_path: false
---

# リファクタリング

## 要求
顧客が借りたビデオのレンタル料金を計算して計算書を印刷する。
```puml
@startuml
left to right direction
skinparam packageStyle rectangle
actor customer
rectangle VedioRental {
  customer -- (Rental)
              (Calculate rental fee)
              (Print a statement)
}
@enduml
```

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
```puml
class Movie {
  priceCode:int
}
class Rental {
  daysRented:int
}
class Customer {
  statement()
}
Movie "1"<-"*" Rental
Rental "*"<-"1" Customer
```

### シーケンス図
statement(計算書生成)メソッドのシーケンス図
```puml
@startuml
-> aCustomer: statement
   activate aCustomer
       aCustomer -> aCustomer :*[for all rental]
       activate aCustomer
           aCustomer -> aRental :getMovie
           aCustomer -> aMovie :getPriceCode
           aCustomer -> aRental :getDaysRented
       deactivate aCustomer
   deactivate aCustomer
@enduml
```
## 実装
### `Movie.java`
@import "../../src/main/java/refactoring/videorental/Movie.java"
### `Rental.java`
@import "../../src/main/java/refactoring/videorental/Rental.java"
### `Customer.java`
@import "../../src/main/java/refactoring/videorental/Customer.java"
