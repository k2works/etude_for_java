  
  
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
+ [ ] **オブジェクトを返すようにする**
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.9053063069169198)  
### シーケンス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e561.png?0.5073297490685269)  
  
## 実装
  
### ふりかえり
  
+ ValueObjectパターンを導入した
+ メンバ変数にプロテクティッドタイプを使って継承したクラスでだけ使えるようにした
+ 条件に該当しないケースた対応するためNullObjectパターンを導入した
  
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
### `FizzBuzzValue.java`
  
```java
package tdd.fizzbuzz;
  
abstract class FizzBuzzValue {
    protected int _number;
    protected String _value;
  
    abstract String execute();
  
    static FizzBuzzValue makeFizzBuzzValue(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return new FizzBuzz(number);
        } else if (number % 5 == 0) {
            return new Buzz(number);
        } else if (number % 3 == 0) {
            return new Fizz(number);
        } else {
            return new NullValue(number);
        }
    }
}
  
```  
### `FizzBuzz.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzz extends FizzBuzzValue {
    public FizzBuzz(int number) {
        _number = number;
        _value = "FizzBuzz";
    }
  
    @Override
    String execute() {
        return _value;
    }
}
  
```  
### `Fizz.java`
  
```java
package tdd.fizzbuzz;
  
public class Fizz extends FizzBuzzValue {
    public Fizz(int number) {
        _number = number;
        _value = "Fizz";
    }
  
    @Override
    String execute() {
        return _value;
    }
}
  
```  
### `Buzz.java`
  
```java
package tdd.fizzbuzz;
  
public class Buzz extends FizzBuzzValue {
    public Buzz(int number) {
        _number = number;
        _value = "Buzz";
    }
  
    @Override
    String execute() {
        return _value;
    }
}
  
```  
### `NullValue.java`
  
```java
package tdd.fizzbuzz;
  
public class NullValue extends FizzBuzzValue {
    public NullValue(int number) {
        _number = number;
        _value = String.valueOf(number);
    }
    @Override
    String execute() {
        return _value;
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
        FizzBuzzValue value;
        results = new String[count + 1];
        for (int i = 0; i <= count; ++i) {
            value = FizzBuzzValue.makeFizzBuzzValue(i);
            results[i] = value.execute();
        }
    }
}
  
```  
  
  
  