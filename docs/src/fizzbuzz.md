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
+ [x] ~~両者で割り切れる場合のテストを作成する~~
+ [x] ~~条件を満たさない場合のテストを作成する~~
+ [x] ~~指定された回数だけ繰り返し実行する場合のテストを作成する~~
+ [x] ~~出力された値を全て保持する~~
+ [x] ~~必要なものだけを公開するようにする~~
+ [x] ~~繰り返し実行する部分を分離する~~
+ [x] ~~新しい条件を追加しやすくする~~
+ [x] ~~オブジェクトを返すようにする~~
+ [x] ~~オブジェクトを演算できるようにする~~
  + [x] $FizzBuzz = {Fizz}\times{Buzz}$
  + [x] $Buzz = \frac{FizzBuzz}{Fizz}$
  + [x] $Fizz = \frac{FizzBuzz}{Buzz}$
+ [x] ~~equals()~~
+ [x] ~~集積の概念を表すオブジェクトを追加して構造をシンプルにする~~
+ [x] ~~仕上げのリファクタリング~~

### クラス図
```puml
@startuml
package fizzbuzz {
class Gateway {
  +Gateway(count :int)
  +getResults() :String[]
  +setSources(source :Expression)
  +reduce(source :Expression)
}
abstract class Value {
  #number :Int
  #value  :String
  +{abstract}execute() :String
  +{static} makeValue()
  #reduce() :Value  
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
class Accumulate {
  ~accumulated
  ~accumulate
  Accumulate(accumulated: Expression, accumulate: Expression)
  +times(mulitiplier: Expression) :Expression
  +divide(divisor: Expression) :Expression
  +reduce() :Value  
}
interface Expression {
  times(multiplier: Expression)
  divide(divisor: Expression)
  reduce() :Value
}
}

Gateway -> Value
Value <|-- Fizz
Value <|-- Buzz
Value <|-- FizzBuzz
Value <|-- NullValue
Accumulate -- Value
Accumulate <- Gateway
Expression <|- Value
Expression <|-- Accumulate

@enduml
```
### シーケンス図
#### #Gateway
```puml
@startuml
   activate Gateway
   -> Gateway :new
   loop for each count
      Gateway -> Value :execute
      activate Value
        Gateway <-- Value :Value
        Gateway <- Gateway :List
      deactivate Value
   end
   deactivate Gateway
@enduml
```
#### #times
```puml
@startuml
   -> Value :times
   activate Value
      Value -> Value :makeValue(number :int)      
   deactivate Value
   <-- Value :Value
@enduml
```

```puml
@startuml
   -> Accumulate :times
   activate Accumulate
      Accumulate -> Accumulate :new
   deactivate Accumulate
   <-- Accumulate :Accumulate
@enduml
```

#### #divide
```puml
@startuml
   -> Value :divide
   activate Value
      Value -> Value :makeValue(number :int)
   deactivate Value
   <-- Value :Value
@enduml
```

```puml
@startuml
   -> Accumulate :divide
   activate Accumulate
      Accumulate -> Accumulate :new
   deactivate Accumulate
   <-- Accumulate :Accumulate
@enduml
```

#### #reduce
```puml
@startuml
   -> Value :makeValue
   activate Gateway
   -> Gateway :reduce(Value)
      Gateway -> Value :reduce
      activate Value
        Gateway <-- Value :Value
      deactivate Value   
   <-- Gateway :Value
   deactivate Gateway
@enduml
```

```puml
@startuml
   -> Value :makeValue
   Value -> Accumulate :plus(added: Value)
   activate Gateway
   -> Gateway :reduce(ValueProduct)
      Gateway -> Accumulate :reduce
      activate Accumulate
        Gateway <-- Accumulate :Value
          activate Value
            Accumulate -> Value :reduce
            Value --> Accumulate :Value
          deactivate Value
      deactivate Value
   <-- Gateway :Value
   deactivate Gateway
@enduml
```


## 実装
### ふりかえり
+ 実行クラスをインスタンスに変更する
+ 結果を配列からコレクションに変更する
+ コレクションの学習テストを実施する
+ reduceメソッドのカプセル化
+ 不適切なメソッド名称の整理
+ クラスの名称を変更
+ パッケージの導入

### `FizzBuzzTest.java`
@import "../../src/test/java/tdd/fizzbuzz/FizzBuzzTest.java"
### `Value.java`
@import "../../src/main/java/tdd/fizzbuzz/Value.java"
### `FizzBuzz.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzz.java"
### `Fizz.java`
@import "../../src/main/java/tdd/fizzbuzz/Fizz.java"
### `Buzz.java`
@import "../../src/main/java/tdd/fizzbuzz/Buzz.java"
### `NullValue.java`
@import "../../src/main/java/tdd/fizzbuzz/NullValue.java"
### `Gateway.java`
@import "../../src/main/java/tdd/fizzbuzz/Gateway.java"
### `Expression.java`
@import "../../src/main/java/tdd/fizzbuzz/Expression.java"
### `Accumulate.java`
@import "../../src/main/java/tdd/fizzbuzz/Accumulate.java"

