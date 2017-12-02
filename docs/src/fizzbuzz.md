---
markdown:
  image_dir: ../assets
  path: ../fizzbuzz.md
  ignore_from_front_matter: true
  absolute_image_path: false
---

# FizzBuzz
## 仕様
+ ３で割り切れる場合は「Fizz」を出力する。
+ ５で割り切れる場合は「Buzz」を出力する。
+ 両者で割り切れる場合は「FizzBuzz」を出力する。
+ 上記以外の場合は与えられた数値を出力する。
+ 指定された回数だけ繰り返し実行する。

## 設計
### TODO
+ [x] ~~クラスを定義する~~
+ [x] ~~３で割り切れる場合のテストを作成する~~
+ [x] ~~５で割り切れる場合のテストを作成する~~
+ [x] ~~両者で割り切れる場合のテストを作成する~~
+ [x] ~~条件を満たさない場合のテストを作成する~~
+ [x] ~~指定された回数だけ繰り返し実行する場合のテストを作成する~~
+ [x] ~~出力された値を全て保持する~~
+ [x] ~~必要なものだけを公開するようにする~~
+ [x] ~~繰り返し実行する部分を分離する~~
+ [x] ~~新しい条件を追加しやすくする~~
+ [x] ~~オブジェクトを返すようにする~~
+ [ ] **オブジェクトを演算できるようにする**
  + [x] $FizzBuzz = {Fizz}\times{Buzz}$
  + [ ] $Buzz = \frac{Fizz}{FizzBuzz}$
  + [ ] $Fizz = \frac{Buzz}{FizzBuzz}$
+ [x] ~~equals()~~

### クラス図
```puml
@startuml
class FizzBuzzExecutor {
  -results :String[ ]
  +{static}getResults() :String[]  
  +{static}excueteByCount(count:int) :String[]
  +{static}reduce(source :Expression)
}
abstract class FizzBuzzValue {
  #number :Int
  #value  :String
  +{abstract}execute() :String
  +{static} makeFizzBuzzValue()
  #times(value: FizzBuzzValue, value: FizzBuzzValue) :Expression
  #divideFraction(value: FizzBuzzValue, value: FizzBuzzValue) :Expression
  #reduce(value :FizzBuzzValue, number: int) :FizzBuzzValue
  +equlas(object :Object) :boolean
  +toString() :String
}
class FizzBuzz {
  #{static} FIZZ_BUZZ = "fizz_buzz"
  +execute() :String
}
class Fizz {
  #{static} FIZZ = "fizz"
  +execute() :String
}
class Buzz {
  #{static} BUZZ = "buzz"  
  +execute() :String  
}
class NullValue {
  +execute() :String  
}
class FizzBuzzValueProduct {
  ~_multiplicand
  ~_multiplier
  FizzBuzzValueProduct(augend: Expression, addend: Expression)
  +times(value: FizzBuzzValue, value: FizzBuzzValue) :Expression
  +divideFraction(value: FizzBuzzValue, value: FizzBuzzValue) :Expression
  +reduce(value :FizzBuzzValue, number: int) :FizzBuzzValue
}
interface Expression {
  times(value: FizzBuzzValue, value: FizzBuzzValue)
  divideFraction(value: FizzBuzzValue, value: FizzBuzzValue)
  reduce(value :FizzBuzzValue, number: int)
}
FizzBuzzExecutor -> FizzBuzzValue
FizzBuzzValue <|-- Fizz
FizzBuzzValue <|-- Buzz
FizzBuzzValue <|-- FizzBuzz
FizzBuzzValue <|-- NullValue
FizzBuzzValueProduct -- FizzBuzzValue
FizzBuzzValueProduct <- FizzBuzzExecutor
Expression <|- FizzBuzzValue
Expression <|-- FizzBuzzValueProduct

@enduml
```
### シーケンス図
#### #executeByCount
```puml
@startuml
   activate FizzBuzzExecutor
   -> FizzBuzzExecutor :executeByCount
   loop for each count
      FizzBuzzExecutor -> FizzBuzzValue :execute
      activate FizzBuzzValue
        FizzBuzzExecutor <-- FizzBuzzValue :FizzBuzzValue
      deactivate FizzBuzzValue
   end
   <-- FizzBuzzExecutor :objectArray
   deactivate FizzBuzzExecutor
@enduml
```
#### #times
```puml
@startuml
   -> FizzBuzzValue :times
   activate FizzBuzzValueProduct
      FizzBuzzValue -> FizzBuzzValueProduct :new(FizzBuzzValue, multiplier: Expression)
      FizzBuzzValueProduct -> FizzBuzzValue :Expression
   deactivate FizzBuzzValueProduct
   <-- FizzBuzzValue :Expression
@enduml
```

```puml
@startuml
   -> FizzBuzzValueProduct :times
   activate FizzBuzzValueProduct
      FizzBuzzValueProduct -> FizzBuzzValueProduct :new(FizzBuzzValueProduct, multiplier: Expression)
   deactivate FizzBuzzValueProduct
   <-- FizzBuzzValueProduct :Expression
@enduml
```

#### #reduce
```puml
@startuml
   -> FizzBuzzValue :makeFizzBuzzValue
   activate FizzBuzzExecutor
   -> FizzBuzzExecutor :reduce(FizzBuzzValue)
      FizzBuzzExecutor -> FizzBuzzValue :reduce
      activate FizzBuzzValue
        FizzBuzzExecutor <-- FizzBuzzValue :FizzBuzzValue
      deactivate FizzBuzzValue   
   <-- FizzBuzzExecutor :FizzBuzzValue
   deactivate FizzBuzzExecutor
@enduml
```

```puml
@startuml
   -> FizzBuzzValue :makeFizzBuzzValue
   -> FizzBuzzValue :makeFizzBuzzValue
   -> FizzBuzzValueProduct :new(FizzBuzzValue,FizzBuzzValue)
   activate FizzBuzzExecutor
   -> FizzBuzzExecutor :reduce(FizzBuzzValueProduct)
      FizzBuzzExecutor -> FizzBuzzValueProduct :reduce
      activate FizzBuzzValueProduct
        FizzBuzzExecutor <-- FizzBuzzValueProduct :FizzBuzzValue
          activate FizzBuzzValue
            FizzBuzzValueProduct -> FizzBuzzValue :reduce
            FizzBuzzValue --> FizzBuzzValueProduct :FizzBuzzValue
          deactivate FizzBuzzValue
      deactivate FizzBuzzValue   
   <-- FizzBuzzExecutor :FizzBuzzValue
   deactivate FizzBuzzExecutor
@enduml
```


## 実装
### ふりかえり
+ オブジェクト等価テストをパスするためにequalメソッドを実装した
+ Expressionインタフェースを導入した
+ 積の概念を表すオブジェクトを実装した
+ ポリモーフィズムを使って明示的なクラスチェックを置き換えた
+ Expressionへの一般化を実施した
+ 積から直接積を求められるようにした

### `FizzBuzzTest.java`
@import "../../src/test/java/tdd/fizzbuzz/FizzBuzzTest.java"
### `FizzBuzzValue.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzzValue.java"
### `FizzBuzz.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzz.java"
### `Fizz.java`
@import "../../src/main/java/tdd/fizzbuzz/Fizz.java"
### `Buzz.java`
@import "../../src/main/java/tdd/fizzbuzz/Buzz.java"
### `NullValue.java`
@import "../../src/main/java/tdd/fizzbuzz/NullValue.java"
### `FizzBuzzExecutor.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzzExecutor.java"
### `Expression.java`
@import "../../src/main/java/tdd/fizzbuzz/Expression.java"
### `FizzBuzzValueProduct.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzzValueProduct.java"


