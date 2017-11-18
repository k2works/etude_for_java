  
  
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
+ [x] ~~出力された値を全て保持する~~
+ [x] ~~必要なものだけを公開するようにする~~
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.9946724628762669)  
### シーケンス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e561.png?0.5501460698044596)  
  
## 実装
  
  
### `FizzBuzzTest.java`
  
```java
package tdd.fizzbuzz;
  
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class FizzBuzzTest {
    @Test
    public void printFizz() {
        FizzBuzz.executeByCount(3);
        assertEquals("Fizz", FizzBuzz.getResults()[3]);
    }
    @Test
    public void notPrintFizz() {
        FizzBuzz.executeByCount(4);
        assertNotEquals("Fizz", FizzBuzz.getResults()[4]);
    }
    @Test
    public void printBuzz() {
        FizzBuzz.executeByCount(5);
        assertEquals("Buzz", FizzBuzz.getResults()[5]);
    }
    @Test
    public void notPrintBuzz() {
        FizzBuzz.executeByCount(6);
        assertNotEquals("Buzz", FizzBuzz.getResults()[6]);
    }
    @Test
    public void printFizzBuzz() {
        FizzBuzz.executeByCount(15);
        assertEquals("FizzBuzz", FizzBuzz.getResults()[15]);
    }
    @Test
    public void notPrintFizzBuzz() {
        FizzBuzz.executeByCount(20);
        assertEquals("Buzz", FizzBuzz.getResults()[20]);
    }
    @Test
    public void printNotSatisfyTheCondition() {
        FizzBuzz.executeByCount(1);
        assertEquals("1", FizzBuzz.getResults()[1]);
    }
    @Test
    public void print100thCountResult() {
        FizzBuzz.executeByCount(100);
        assertEquals("Buzz", FizzBuzz.getResults()[100]);
    }
    @Test
    public void print30thCountResult() {
        FizzBuzz.executeByCount(30);
        assertEquals("FizzBuzz", FizzBuzz.getResults()[30]);
    }
}
  
```  
### `FizzBuzz.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzz {
    private static String[] results;
  
    public static String[] getResults() {
        return results;
    }
  
    private static String execute(int number) {
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
  
  