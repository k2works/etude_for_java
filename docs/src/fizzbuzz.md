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

## 設計
### TODO
+ [x] ~~クラスを定義する~~
+ [ ] ３で割り切れる場合のテストを作成する
+ [ ] ５で割り切れる場合のテストを作成する
+ [ ] 両者で割り切れる場合のテストを作成する

### クラス図
```puml
@startuml
class FizzBuzz {
  +execute(number:int) :int
}
@enduml
```

## 実装

### `FizzBuzzTest.java`
@import "../../src/test/java/tdd/fizzbuzz/FizzBuzzTest.java"
### `FizzBuzz.java`
@import "../../src/main/java/tdd/fizzbuzz/FizzBuzz.java"

