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
+ [x] ~~オブジェクトを演算できるようにする~~
  + [x] $FizzBuzz = {Fizz}\times{Buzz}$
  + [x] $Buzz = \frac{FizzBuzz}{Fizz}$
  + [x] $Fizz = \frac{FizzBuzz}{Buzz}$
+ [x] ~~equals()~~
+ [x] ~~集積の概念を表すオブジェクトを追加して構造をシンプルにする~~
+ [ ] **仕上げのリファクタリング**

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
  #reduce() :FizzBuzzValue  
  #times(multiplier: Expression) :Expression
  #divide(divisor: Expression) :Expression
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
class FizzBuzzValueAccumulate {
  ~accumulated
  ~accumulate
  FizzBuzzValueAccumulate(accumulated: Expression, accumulate: Expression)
  +times(mulitiplier: Expression) :Expression
  +divide(divisor: Expression) :Expression
  +reduce() :FizzBuzzValue  
}
interface Expression {
  times(multiplier: Expression)
  divide(divisor: Expression)
  reduce() :FizzBuzzValue
}
FizzBuzzExecutor -> FizzBuzzValue
FizzBuzzValue <|-- Fizz
FizzBuzzValue <|-- Buzz
FizzBuzzValue <|-- FizzBuzz
FizzBuzzValue <|-- NullValue
FizzBuzzValueAccumulate -- FizzBuzzValue
FizzBuzzValueAccumulate <- FizzBuzzExecutor
Expression <|- FizzBuzzValue
Expression <|-- FizzBuzzValueAccumulate

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
   activate FizzBuzzValue
      FizzBuzzValue -> FizzBuzzValue :makeFizzBuzzValue(number :int)      
   deactivate FizzBuzzValue
   <-- FizzBuzzValue :FizzBuzzValue
@enduml
```

```puml
@startuml
   -> FizzBuzzValueAccumulate :times
   activate FizzBuzzValueAccumulate
      FizzBuzzValueAccumulate -> FizzBuzzValueAccumulate :new
   deactivate FizzBuzzValueAccumulate
   <-- FizzBuzzValueAccumulate :FizzBuzzValueAccumulate
@enduml
```

#### #divide
```puml
@startuml
   -> FizzBuzzValue :divide
   activate FizzBuzzValue
      FizzBuzzValue -> FizzBuzzValue :makeFizzBuzzValue(number :int)
   deactivate FizzBuzzValue
   <-- FizzBuzzValue :FizzBuzzValue
@enduml
```

```puml
@startuml
   -> FizzBuzzValueAccumulate :divide
   activate FizzBuzzValueAccumulate
      FizzBuzzValueAccumulate -> FizzBuzzValueAccumulate :new
   deactivate FizzBuzzValueAccumulate
   <-- FizzBuzzValueAccumulate :FizzBuzzValueAccumulate
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
   FizzBuzzValue -> FizzBuzzValueAccumulate :plus(added: FizzBuzzValue)
   activate FizzBuzzExecutor
   -> FizzBuzzExecutor :reduce(FizzBuzzValueProduct)
      FizzBuzzExecutor -> FizzBuzzValueAccumulate :reduce
      activate FizzBuzzValueAccumulate
        FizzBuzzExecutor <-- FizzBuzzValueAccumulate :FizzBuzzValue
          activate FizzBuzzValue
            FizzBuzzValueAccumulate -> FizzBuzzValue :reduce
            FizzBuzzValue --> FizzBuzzValueAccumulate :FizzBuzzValue
          deactivate FizzBuzzValue
      deactivate FizzBuzzValue
   <-- FizzBuzzExecutor :FizzBuzzValue
   deactivate FizzBuzzExecutor
@enduml
```


## 実装
### ふりかえり
+ 実行クラスをインスタンスに変更する
+ 結果を配列からコレクションに変更する

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
### `FizzBuzzValueAccumulate.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzzValueAccumulate.java"

