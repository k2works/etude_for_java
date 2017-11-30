  
  
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
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b0.png?0.2824543273684368)  
### シーケンス図
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b1.png?0.4961534090602302)  
  
## 実装
  
  
### `FizzBuzzTest.java`
  
```java
package tdd.fizzbuzz;
  
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class FizzBuzzTest {
    @Test
    public void printFizz() {
        FizzBuzzExecutor.executeByCount(3);
        assertEquals("Fizz", FizzBuzzExecutor.getResults()[3]);
    }
    @Test
    public void notPrintFizz() {
        FizzBuzzExecutor.executeByCount(4);
        assertNotEquals("Fizz", FizzBuzzExecutor.getResults()[4]);
    }
    @Test
    public void printBuzz() {
        FizzBuzzExecutor.executeByCount(5);
        assertEquals("Buzz", FizzBuzzExecutor.getResults()[5]);
    }
    @Test
    public void notPrintBuzz() {
        FizzBuzzExecutor.executeByCount(6);
        assertNotEquals("Buzz", FizzBuzzExecutor.getResults()[6]);
    }
    @Test
    public void printFizzBuzz() {
        FizzBuzzExecutor.executeByCount(15);
        assertEquals("FizzBuzz", FizzBuzzExecutor.getResults()[15]);
    }
    @Test
    public void notPrintFizzBuzz() {
        FizzBuzzExecutor.executeByCount(20);
        assertEquals("Buzz", FizzBuzzExecutor.getResults()[20]);
    }
    @Test
    public void printNotSatisfyTheCondition() {
        FizzBuzzExecutor.executeByCount(1);
        assertEquals("1", FizzBuzzExecutor.getResults()[1]);
    }
    @Test
    public void print100thCountResult() {
        FizzBuzzExecutor.executeByCount(100);
        assertEquals("Buzz", FizzBuzzExecutor.getResults()[100]);
    }
    @Test
    public void print30thCountResult() {
        FizzBuzzExecutor.executeByCount(30);
        assertEquals("FizzBuzz", FizzBuzzExecutor.getResults()[30]);
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
        } else {
            return String.valueOf(number);
        }
    }
}
  
```  
### `FizzBuzzExecutor.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzzExecutor {
    private static String[] results;
  
    public static String[] getResults() {
        return results;
    }
  
    public static void executeByCount(int count) {
        results = new String[count + 1];
        for (int i = 0; i <= count; ++i) {
            results[i] = FizzBuzz.execute(i);
        }
    }
}
  
```  
  
  
  