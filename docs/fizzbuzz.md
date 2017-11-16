  
  
# FizzBuzz
  
## 仕様
  
+ ３で割り切れる場合は「Fizz」を出力する。
+ ５で割り切れる場合は「Buzz」を出力する。
+ 両者で割り切れる場合は「FizzBuzz」を出力する。
  
## 設計
  
### TODO
  
+ [x] ~~クラスを定義する~~
+ [ ] **３で割り切れる場合のテストを作成する**
+ [ ] ５で割り切れる場合のテストを作成する
+ [ ] 両者で割り切れる場合のテストを作成する
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.6295560150349389)  
  
## 実装
  
  
### `FizzBuzzTest.java`
  
```java
package tdd.fizzbuzz;
  
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class FizzBuzzTest {
    @Test
    public void printFizz() {
        String result = FizzBuzz.execute(3);
        assertEquals("Fizz",result);
    }
}
  
```  
### `FizzBuzz.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzz {
  
    public static String execute(int number) {
        return "Fizz";
    }
}
  
```  
  
  