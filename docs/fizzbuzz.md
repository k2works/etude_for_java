  
  
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
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.30787987220899304)  
### シーケンス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e561.png?0.598274863653192)  
  
## 実装
  
### ふりかえり
  
+ オープン・クローズドの原則(OCP)に従い設計を変更した
+ 抽象クラスを作成した
+ 抽象クラスを継承したクラスを作成するにあたってインスタンスを生成するようにした
+ 文字列を返す仕様は変更していないのでテストを壊すことなくアプリケーション構造を変更した
+ 単一責任の原則(SRP)に従いファクトリメソッドにインスタンス生成判定をメソッド移動した
  
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
    abstract String execute();
  
    static FizzBuzzValue makeFizzBuzzValue(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return new FizzBuzz();
        } else if (number % 5 == 0) {
            return new Buzz();
        } else if (number % 3 == 0) {
            return new Fizz();
        } else {
            return null;
        }
    }
}
  
```  
### `FizzBuzz.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzz extends FizzBuzzValue {
    @Override
    String execute() {
        return "FizzBuzz";
    }
}
  
```  
### `Fizz.java`
  
```java
package tdd.fizzbuzz;
  
public class Fizz extends FizzBuzzValue {
    @Override
    String execute() {
        return "Fizz";
    }
}
  
```  
### `Buzz.java`
  
```java
package tdd.fizzbuzz;
  
public class Buzz extends FizzBuzzValue {
    @Override
    String execute() {
        return "Buzz";
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
            if (value == null) {
                results[i] = String.valueOf(i);
            } else {
                results[i] = value.execute();
            }
        }
    }
}
  
```  
  
  
  