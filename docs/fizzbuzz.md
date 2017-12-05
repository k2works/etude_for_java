  
  
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
+ [ ] **仕上げのリファクタリング**
  
### クラス図
  

![](../assets/40b84e327c2d377e13e9a6639722fa8b0.png?0.4253870326003564)  
### シーケンス図
  
#### #FizzBuzzGateway
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b1.png?0.9469275314805472)  
#### #times
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b2.png?0.9924742526710641)  
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b3.png?0.24693410612951538)  
  
#### #divide
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b4.png?0.8686967405082322)  
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b5.png?0.37844817427950805)  
  
#### #reduce
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b6.png?0.07122400260322914)  
  

![](./assets/40b84e327c2d377e13e9a6639722fa8b7.png?0.8629681070899449)  
  
  
## 実装
  
### ふりかえり
  
+ 実行クラスをインスタンスに変更する
+ 結果を配列からコレクションに変更する
+ コレクションの学習テストを実施する
+ reduceメソッドのカプセル化
+ 不適切なメソッド名称の整理
+ クラスの名称を変更
  
### `FizzBuzzTest.java`
  
```java
package tdd.fizzbuzz;
  
import org.junit.jupiter.api.Test;
import java.util.List;
  
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
  
public class FizzBuzzTest {
    private FizzBuzzExecutor _executor;
  
    private FizzBuzzValue getValue(int count) {
        return _executor.getResults().get(count);
    }
  
    private void setup(Integer count) {
        _executor = new FizzBuzzExecutor(count);
    }
  
    @Test
    public void printFizz() {
        int count = 3;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertEquals("Fizz", value.execute());
    }
    @Test
    public void notPrintFizz() {
        int count = 4;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertNotEquals("Fizz", value.execute());
    }
    @Test
    public void printBuzz() {
        int count = 5;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void notPrintBuzz() {
        setup(6);
        FizzBuzzValue value = getValue(6);
        assertNotEquals("Buzz", value.execute());
    }
    @Test
    public void printFizzBuzz() {
        int count = 15;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertEquals("FizzBuzz", value.execute());
    }
    @Test
    public void notPrintFizzBuzz() {
        int count = 20;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void printNotSatisfyTheCondition() {
        int count = 1;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertEquals("1", value.execute());
    }
    @Test
    public void print100thCountResult() {
        int count = 100;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void print30thCountResult() {
        int count = 30;
        setup(count);
        FizzBuzzValue value = getValue(count);
        assertEquals("FizzBuzz", value.execute());
    }
    @Test
    public void simpleMultiplication() {
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        FizzBuzzValue result = (FizzBuzzValue) fizz.times(buzz);
        assertEquals(fizzBuzz, result.reduce());
    }
    @Test
    public void reduceProduct() {
        setup(0);
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        _executor.setSources(fizz);
        _executor.setSources(buzz);
        FizzBuzzValue result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void reduceValue() {
        setup(0);
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        _executor.setSources(fizz);
        FizzBuzzValue result = _executor.reduce();
        assertEquals(FizzBuzzValue.makeFizzBuzzValue(3), result);
    }
    @Test
    public void mixedMultiple() {
        setup(0);
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        _executor.setSources(fizz.times(buzz));
        FizzBuzzValue result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void productTimesValue() {
        setup(0);
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(45);
        _executor.setSources(fizz);
        _executor.setSources(buzz);
        _executor.setSources(fizz);
        FizzBuzzValue result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void simpleDivision() {
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
  
        FizzBuzzValue result = (FizzBuzzValue) fizzBuzz.divide(fizz);
        assertEquals(buzz, result);
  
        result = (FizzBuzzValue) fizzBuzz.divide(buzz);
        assertEquals(fizz, result);
    }
    @Test
    public void quotientDivideValue() {
        setup(0);
        Expression fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(45);
        Expression fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        Expression buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        _executor.setSources(fizzBuzz.divide(fizz).divide(fizz));
  
        FizzBuzzValue result = _executor.reduce();
        assertEquals(buzz, result);
    }
    @Test
    public void simpleAccumulateValue(){
        setup(0);
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
  
        _executor.setSources(fizz);
        _executor.setSources(buzz);
        FizzBuzzValue result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void studyListApi() {
        int count = 10;
        setup(count);
        List<FizzBuzzValue> results = _executor.getResults();
        FizzBuzzValue fizz = FizzBuzzValue.makeFizzBuzzValue(3);
        FizzBuzzValue buzz = FizzBuzzValue.makeFizzBuzzValue(5);
        FizzBuzzValue fizzBuzz = FizzBuzzValue.makeFizzBuzzValue(15);
  
        assertEquals(fizz, results.get(3));
        results.add(11,fizzBuzz);
        assertEquals(fizzBuzz, results.get(11));
        results.set(11,buzz);
        assertEquals(buzz, results.get(11));
        results.remove(11);
        assertEquals(false,results.contains(fizzBuzz));
        assertEquals(true,results.contains(fizz));
        assertEquals(true,results.contains(buzz));
        assertEquals(false, results.isEmpty());
        assertEquals(11,results.size());
        results.clear();
        assertEquals(true, results.isEmpty());
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
### `FizzBuzzGateway.java`
  
```java
package tdd.fizzbuzz;
  
import java.util.ArrayList;
import java.util.List;
  
public class FizzBuzzGateway {
    private List<FizzBuzzValue> _results;
    private Expression _accumulated;
    private List<Expression> _sources;
  
    public FizzBuzzGateway(Integer count) {
        Integer _count = count;
        _results = new ArrayList<>();
        _sources = new ArrayList<>();
  
        for (int i = 0; i <= _count; ++i) {
            FizzBuzzValue value = FizzBuzzValue.makeFizzBuzzValue(i);
            _results.add(value);
        }
    }
  
    public List<FizzBuzzValue> getResults() {
        return _results;
    }
  
    public void setSources(Expression source) {
        if (_accumulated == null) {
            _accumulated = source;
        } else {
            source = new FizzBuzzValueAccumulate(_accumulated, source);
            _sources.add(source);
            _accumulated = source;
        }
    }
  
    public FizzBuzzValue reduce() {
        FizzBuzzValue value = null;
  
        if (_sources.isEmpty())
            return _accumulated.reduce();
  
        for (Expression source : _sources)
            value = source.reduce();
  
        return value;
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
  
  