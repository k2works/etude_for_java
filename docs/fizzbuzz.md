  
  
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
+ [x] ~~オブジェクトを演算できるようにする~~
  + [x] <img src="https://latex.codecogs.com/gif.latex?FizzBuzz%20=%20{Fizz}&#x5C;times{Buzz}"/>
  + [x] <img src="https://latex.codecogs.com/gif.latex?Buzz%20=%20&#x5C;frac{FizzBuzz}{Fizz}"/>
  + [x] <img src="https://latex.codecogs.com/gif.latex?Fizz%20=%20&#x5C;frac{FizzBuzz}{Buzz}"/>
+ [x] ~~equals()~~
+ [x] ~~集積の概念を表すオブジェクトを追加して構造をシンプルにする~~
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.6548352639738342)  
### シーケンス図
  
#### #executeByCount
  

![](./assets/e8d064149b1f1533be1aa0a12f272e561.png?0.7582185988329189)  
#### #times
  

![](./assets/e8d064149b1f1533be1aa0a12f272e562.png?0.03828165870966593)  
  

![](./assets/e8d064149b1f1533be1aa0a12f272e563.png?0.3374008541763258)  
  
#### #divide
  

![](./assets/e8d064149b1f1533be1aa0a12f272e564.png?0.9869126501141594)  
  

![](./assets/e8d064149b1f1533be1aa0a12f272e565.png?0.7245123740961841)  
  
#### #reduce
  

![](./assets/e8d064149b1f1533be1aa0a12f272e566.png?0.06524803215302977)  
  

![](./assets/e8d064149b1f1533be1aa0a12f272e567.png?0.7831505281913316)  
  
  
## 実装
  
### ふりかえり
  
+ 商と積を表す概念の責務を集積の概念にクラスのインラインすることで構造をシンプルにした
+ 商を表す振る舞いが変わったがむしろ意図に沿っているので問題はない
+ クラスのインライン化リファクタリングによりテストを壊さずにクラスを削除できた
  
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
    @Test
    public void simpleMultiplication() {
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue result = (FizzBuzzValue) fizz.times(buzz);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        assertEquals(fizzBuzz, result.reduce());
    }
    @Test
    public void reduceProduct() {
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        Expression product = new FizzBuzzValueAccumulate(fizz,buzz);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(product);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void reduceValue() {
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(fizz);
        assertEquals(FizzBuzzValue.makeFizzBuzzValue(3), result);
    }
    @Test
    public void mixedMultiple() {
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(fizz.times(buzz));
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void productTimesValue() {
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        Expression product = new FizzBuzzValueAccumulate(fizz,buzz).times(fizz);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(product);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(45);
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void simpleDivision() {
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(fizzBuzz.divide(fizz));
  
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        assertEquals(buzz, result);
        result = FizzBuzzExecutor.reduce(fizzBuzz.divide(buzz));
        assertEquals(fizz, result);
    }
    @Test
    public void quotientDivideValue() {
        Expression fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        Expression quotient = new FizzBuzzValueAccumulate(fizzBuzz,fizz);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(quotient.divide(fizz));
        fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(5);
        assertEquals(fizzBuzz, result);
  
    }
    @Test
    public void simpleAccumulateValue(){
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        Expression product = new FizzBuzzValueAccumulate(fizz,buzz);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(product);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        assertEquals(fizzBuzz, result);
    }
}
  
```  
### `FizzBuzzValue.java`
  
```java
package tdd.fizzbuzz;
  
abstract class FizzBuzzValue implements Expression {
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
  
    public boolean equals(Object object) {
        FizzBuzzValue value = (FizzBuzzValue) object;
        return _number == value._number
                && _value.equals(value._value);
    }
  
    public String toString() {
        return _number + " " + _value;
    }
  
    public Expression times(Expression multiplier) {
        int number = this.reduce()._number * multiplier.reduce()._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }
  
    public Expression divide(Expression divisor) {
        int number = this.reduce()._number / divisor.reduce()._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }
  
    public FizzBuzzValue reduce() {
        return this;
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
  
    public static FizzBuzzValue reduce(Expression source) {
        return source.reduce();
    }
}
  
```  
### `Expression.java`
  
```java
package tdd.fizzbuzz;
  
public interface Expression {
    FizzBuzzValue reduce();
  
    Expression times(Expression multiplier);
  
    Expression divide(Expression divisor);
}
  
```  
### `FizzBuzzValueAccumulate.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzzValueAccumulate implements Expression {
    Expression _accumulated;
    Expression _accumulate;
  
    FizzBuzzValueAccumulate(Expression accumulated, Expression accumulate) {
        _accumulated = accumulated;
        _accumulate = accumulate;
    }
  
    @Override
    public Expression times(Expression multiplier) {
        return new FizzBuzzValueAccumulate(this, multiplier);
    }
  
    @Override
    public Expression divide(Expression divisor) {
        return new FizzBuzzValueAccumulate(_accumulated.divide(divisor), _accumulate.divide(divisor));
    }
  
    @Override
    public FizzBuzzValue reduce() {
        int number = _accumulated.reduce()._number * _accumulate.reduce()._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }
}
  
```  
  
  