  
  
# FizzBuzz
  
## 仕様
  
+ ３で割り切れる場合は「Fizz」を出力する。
+ ５で割り切れる場合は「Buzz」を出力する。
+ 両者で割り切れる場合は「FizzBuzz」を出力する。
+ 上記以外の場合は与えられた数値を出力する。
+ 指定された回数だけ繰り返し実行する。
  
## 設計
  
### TODO
  
+ [x] ~~クラスを定義する~~
+ [x] ~~３で割り切れる場合のテストを作成する~~
+ [x] ~~５で割り切れる場合のテストを作成する~~
+ [x] ~~両者で割り切れる場合のテストを作成する~~
+ [ ] **条件を満たさない場合のテストを作成する**
+ [ ] 指定された回数だけ繰り返し実行する場合のテストを作成する
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.6295560150349389)  
  
## 実装
  
  
### `FizzBuzzTest.java`
  
```java
package tdd.fizzbuzz;
  
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class FizzBuzzTest {
    @Test
    public void printFizz() {
        String result = FizzBuzz.execute(3);
        assertEquals("Fizz",result);
    }
    @Test
    public void notPrintFizz() {
        String result = FizzBuzz.execute(4);
        assertNotEquals("Fizz",result);
    }
    @Test
    public void printBuzz() {
        String result = FizzBuzz.execute(5);
        assertEquals("Buzz",result);
    }
    @Test
    public void notPrintBuzz() {
        String result = FizzBuzz.execute(6);
        assertNotEquals("Buzz",result);
    }
    @Test
    public void printFizzBuzz() {
        String result = FizzBuzz.execute(15);
        assertEquals("FizzBuzz",result);
    }
    @Test
    public void notPrintFizzBuzz() {
        String result = FizzBuzz.execute(20);
        assertNotEquals("FizzBuzz",result);
    }
    @Test
    public void printNotSatisfyTheCondition() {
        String result = FizzBuzz.execute(1);
        assertEquals(1,result);
    }
}
  
```  
### `FizzBuzz.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzz {
  
    public static String execute(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else  {
            return null;
        }
    }
}
  
```  
  
  