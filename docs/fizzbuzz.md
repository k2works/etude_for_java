  
  
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
+ [x] ~~条件を満たさない場合のテストを作成する~~
+ [x] ~~指定された回数だけ繰り返し実行する場合のテストを作成する~~
+ [ ] **出力された値を全て保持する**
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.975584999148216)  
### シーケンス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e561.png?0.3916987160985095)  
  
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
        assertEquals("1",result);
    }
    @Test
    public void print100thCountResult() {
        FizzBuzz.executeByCount(100);
        assertEquals("Buzz",FizzBuzz.results[100]);
    }
    @Test
    public void print30thCountResult() {
        FizzBuzz.executeByCount(30);
        assertEquals("FizzBuzz",FizzBuzz.results[30]);
    }
}
  
```  
### `FizzBuzz.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzz {
  
    public static String[] results;
  
    public static String execute(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else {
            return String.valueOf(number);
        }
    }
  
    public static void executeByCount(int count) {
        results = new String[count + 1];
        for (int i = 0; i <= count; ++i) {
            results[i] = FizzBuzz.execute(i);
        }
    }
}
  
```  
  
  