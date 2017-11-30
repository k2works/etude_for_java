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
+ [ ] **新しい条件を追加しやすくする**

### クラス図
```puml
@startuml
class FizzBuzzExecutor {
  -results :String[ ]
  +getResults() :String[]  
  +excueteByCount(count:int) :String[]
}
abstract class FizzBuzzValue {
  +execute(number:int) :String
}
class FizzBuzz {
  +execute(number:int) :String
}
class Fizz {
  +execute(number:int) :String
}
class Buzz {
  +execute(number:int) :String  
}
FizzBuzzExecutor -> FizzBuzzValue
FizzBuzzValue <|-- Fizz
FizzBuzzValue <|-- Buzz
FizzBuzzValue <|-- FizzBuzz

@enduml
```
### シーケンス図
```puml
@startuml
   activate FizzBuzzExecutor
   -> FizzBuzzExecutor :executeByCount
   loop for each count
      FizzBuzzExecutor -> FizzBuzzValue :execute
      activate FizzBuzzValue
        FizzBuzzExecutor <-- FizzBuzzValue :stringValue
      deactivate FizzBuzzValue
   end
   <-- FizzBuzzExecutor :stringArray
   deactivate FizzBuzzExecutor
@enduml
```

## 実装

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
### `FizzBuzzExecutor.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzzExecutor.java"


