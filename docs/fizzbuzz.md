  
  
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
+ [x] ~~オブジェクトを返すようにする~~
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.16467176386997417)  
### シーケンス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e561.png?0.10912989986891652)  
  
## 実装
  
### ふりかえり
  
+ ValueObjectパターンを導入した
+ メンバ変数にプロテクティッドタイプを使って継承したクラスでだけ使えるようにした
+ 条件に該当しないケースた対応するためNullObjectパターンを導入した
+ オブジェクト配列を返すので文字型からObject型に配列の定義を変更した
+ 配列を受け取る際に元のオブジェクトが何かを明示すひ必要があるので型のキャストを実行した
+ リファクタリング（ローカル変数の抽出・マジックナンバーからシンボル定数へ）を実施した
  
### `FizzBuzzTest.java`
  
```java
package tdd.fizzbuzz;
  
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
  
public class FizzBuzzTest {
    @Test
    public void printFizz() {
        int count = 3;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertEquals("Fizz", value.execute());
    }
    @Test
    public void notPrintFizz() {
        int count = 4;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertNotEquals("Fizz", value.execute());
    }
    @Test
    public void printBuzz() {
        int count = 5;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void notPrintBuzz() {
        FizzBuzzExecutor.executeByCount(6);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[6];
        assertNotEquals("Buzz", value.execute());
    }
    @Test
    public void printFizzBuzz() {
        int count = 15;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertEquals("FizzBuzz", value.execute());
    }
    @Test
    public void notPrintFizzBuzz() {
        int count = 20;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void printNotSatisfyTheCondition() {
        int count = 1;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertEquals("1", value.execute());
    }
    @Test
    public void print100thCountResult() {
        int count = 100;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void print30thCountResult() {
        int count = 30;
        FizzBuzzExecutor.executeByCount(count);
        FizzBuzzValue value = (FizzBuzzValue)FizzBuzzExecutor.getResults()[count];
        assertEquals("FizzBuzz", value.execute());
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
  
    public static final String FIZZ_BUZZ = "FizzBuzz";
  
    public FizzBuzz(int number) {
        _number = number;
        _value = FIZZ_BUZZ;
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
  
    public static final String FIZZ = "Fizz";
  
    public Fizz(int number) {
        _number = number;
        _value = FIZZ;
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
  
    public static final String BUZZ = "Buzz";
  
    public Buzz(int number) {
        _number = number;
        _value = BUZZ;
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
    private static Object[] results;
  
    public static Object[] getResults() {
        return results;
    }
  
    public static void executeByCount(int count) {
        results = new Object[count + 1];
        for (int i = 0; i <= count; ++i) {
            FizzBuzzValue value = FizzBuzzValue.makeFizzBuzzValue(i);
            results[i] = value;
        }
    }
}
  
```  
  
  
  