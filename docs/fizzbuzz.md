  
  
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
+ [ ] **オブジェクトを演算できるようにする**
  + [x] <img src="https://latex.codecogs.com/gif.latex?FizzBuzz%20=%20{Fizz}&#x5C;times{Buzz}"/>
  + [ ] <img src="https://latex.codecogs.com/gif.latex?Buzz%20=%20&#x5C;frac{FizzBuzz}{Fizz}"/>
  + [ ] <img src="https://latex.codecogs.com/gif.latex?Fizz%20=%20&#x5C;frac{FizzBuzz}{Buzz}"/>
+ [x] ~~equals()~~
  
### クラス図
  

![](./assets/e8d064149b1f1533be1aa0a12f272e560.png?0.8983028533358803)  
### シーケンス図
  
#### #executeByCount
  

![](./assets/e8d064149b1f1533be1aa0a12f272e561.png?0.20216168735632922)  
#### #times
  

![](./assets/e8d064149b1f1533be1aa0a12f272e562.png?0.1780558170784381)  
  

![](./assets/e8d064149b1f1533be1aa0a12f272e563.png?0.24818821722410878)  
  
#### #reduce
  

![](./assets/e8d064149b1f1533be1aa0a12f272e564.png?0.2971188419617259)  
  

![](./assets/e8d064149b1f1533be1aa0a12f272e565.png?0.39799201100734716)  
  
  
## 実装
  
  
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
        FizzBuzzValueProduct result = (FizzBuzzValueProduct) fizz.times(buzz);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        assertEquals(fizzBuzz, result.reduce());
    }
    @Test
    public void timesReturnsProduct() {
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        Expression result = fizzBuzz.times(fizzBuzz);
        FizzBuzzValueProduct fizzBuzzValueProduct = (FizzBuzzValueProduct) result;
        assertEquals(fizzBuzz, fizzBuzzValueProduct._multiplicand);
        assertEquals(fizzBuzz, fizzBuzzValueProduct._multiplier);
    }
    @Test
    public void reduceProduct() {
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        Expression product = new FizzBuzzValueProduct(fizz,buzz);
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
        Expression product = new FizzBuzzValueProduct(fizz,buzz).times(fizz);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(product);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(45);
        assertEquals(fizzBuzz, result);
    }
    public void simpleDivision() {
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        FizzBuzzValue result = FizzBuzzExecutor.reduce(fizz.times(buzz));
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
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
        return new FizzBuzzValueProduct(this, multiplier);
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
}
  
```  
### `FizzBuzzValueProduct.java`
  
```java
package tdd.fizzbuzz;
  
public class FizzBuzzValueProduct implements Expression {
    Expression _multiplicand;
    Expression _multiplier;
  
    FizzBuzzValueProduct(Expression multiplicand, Expression multiplier) {
        _multiplicand = multiplicand;
        _multiplier = multiplier;
    }
    public FizzBuzzValue reduce() {
        int number = _multiplicand.reduce()._number * _multiplier.reduce()._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }
  
    @Override
    public Expression times(Expression multiplier) {
        return new FizzBuzzValueProduct(this, multiplier);
    }
}
  
```  
  
  
  