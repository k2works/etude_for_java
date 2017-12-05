package tdd.fizzbuzz;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FizzBuzzTest {
    private Gateway _executor;

    private Value getValue(int count) {
        return _executor.getResults().get(count);
    }

    private void setup(Integer count) {
        _executor = new Gateway(count);
    }

    @Test
    public void printFizz() {
        int count = 3;
        setup(count);
        Value value = getValue(count);
        assertEquals("Fizz", value.execute());
    }
    @Test
    public void notPrintFizz() {
        int count = 4;
        setup(count);
        Value value = getValue(count);
        assertNotEquals("Fizz", value.execute());
    }
    @Test
    public void printBuzz() {
        int count = 5;
        setup(count);
        Value value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void notPrintBuzz() {
        setup(6);
        Value value = getValue(6);
        assertNotEquals("Buzz", value.execute());
    }
    @Test
    public void printFizzBuzz() {
        int count = 15;
        setup(count);
        Value value = getValue(count);
        assertEquals("FizzBuzz", value.execute());
    }
    @Test
    public void notPrintFizzBuzz() {
        int count = 20;
        setup(count);
        Value value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void printNotSatisfyTheCondition() {
        int count = 1;
        setup(count);
        Value value = getValue(count);
        assertEquals("1", value.execute());
    }
    @Test
    public void print100thCountResult() {
        int count = 100;
        setup(count);
        Value value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void print30thCountResult() {
        int count = 30;
        setup(count);
        Value value = getValue(count);
        assertEquals("FizzBuzz", value.execute());
    }
    @Test
    public void simpleMultiplication() {
        Value fizz = Value.makeFizzBuzzValue(3);
        Value buzz = Value.makeFizzBuzzValue(5);
        Value fizzBuzz = Value.makeFizzBuzzValue(15);
        Value result = (Value) fizz.times(buzz);
        assertEquals(fizzBuzz, result.reduce());
    }
    @Test
    public void reduceProduct() {
        setup(0);
        Value fizz = Value.makeFizzBuzzValue(3);
        Value buzz = Value.makeFizzBuzzValue(5);
        Value fizzBuzz = Value.makeFizzBuzzValue(15);
        _executor.setSources(fizz);
        _executor.setSources(buzz);
        Value result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void reduceValue() {
        setup(0);
        Value fizz = Value.makeFizzBuzzValue(3);
        _executor.setSources(fizz);
        Value result = _executor.reduce();
        assertEquals(Value.makeFizzBuzzValue(3), result);
    }
    @Test
    public void mixedMultiple() {
        setup(0);
        Expression fizz = Value.makeFizzBuzzValue(3);
        Expression buzz = Value.makeFizzBuzzValue(5);
        Value fizzBuzz = Value.makeFizzBuzzValue(15);
        _executor.setSources(fizz.times(buzz));
        Value result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void productTimesValue() {
        setup(0);
        Expression fizz = Value.makeFizzBuzzValue(3);
        Expression buzz = Value.makeFizzBuzzValue(5);
        Value fizzBuzz = Value.makeFizzBuzzValue(45);
        _executor.setSources(fizz);
        _executor.setSources(buzz);
        _executor.setSources(fizz);
        Value result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void simpleDivision() {
        Value fizzBuzz = Value.makeFizzBuzzValue(15);
        Expression fizz = Value.makeFizzBuzzValue(3);
        Expression buzz = Value.makeFizzBuzzValue(5);

        Value result = (Value) fizzBuzz.divide(fizz);
        assertEquals(buzz, result);

        result = (Value) fizzBuzz.divide(buzz);
        assertEquals(fizz, result);
    }
    @Test
    public void quotientDivideValue() {
        setup(0);
        Expression fizzBuzz = Value.makeFizzBuzzValue(45);
        Expression fizz = Value.makeFizzBuzzValue(3);
        Expression buzz = Value.makeFizzBuzzValue(5);
        _executor.setSources(fizzBuzz.divide(fizz).divide(fizz));

        Value result = _executor.reduce();
        assertEquals(buzz, result);
    }
    @Test
    public void simpleAccumulateValue(){
        setup(0);
        Value fizz = Value.makeFizzBuzzValue(3);
        Value buzz = Value.makeFizzBuzzValue(5);
        Value fizzBuzz = Value.makeFizzBuzzValue(15);

        _executor.setSources(fizz);
        _executor.setSources(buzz);
        Value result = _executor.reduce();
        assertEquals(fizzBuzz, result);
    }
    @Test
    public void studyListApi() {
        int count = 10;
        setup(count);
        List<Value> results = _executor.getResults();
        Value fizz = Value.makeFizzBuzzValue(3);
        Value buzz = Value.makeFizzBuzzValue(5);
        Value fizzBuzz = Value.makeFizzBuzzValue(15);

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
