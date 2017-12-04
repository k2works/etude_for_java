package tdd.fizzbuzz;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
        assertEquals("Fizz", value.execute());
    }
    @Test
    public void notPrintFizz() {
        int count = 4;
        setup(count);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
        assertNotEquals("Fizz", value.execute());
    }
    @Test
    public void printBuzz() {
        int count = 5;
        setup(count);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void notPrintBuzz() {
        setup(6);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(6);
        assertNotEquals("Buzz", value.execute());
    }
    @Test
    public void printFizzBuzz() {
        int count = 15;
        setup(count);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
        assertEquals("FizzBuzz", value.execute());
    }
    @Test
    public void notPrintFizzBuzz() {
        int count = 20;
        setup(count);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void printNotSatisfyTheCondition() {
        int count = 1;
        setup(count);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
        assertEquals("1", value.execute());
    }
    @Test
    public void print100thCountResult() {
        int count = 100;
        setup(count);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
        assertEquals("Buzz", value.execute());
    }
    @Test
    public void print30thCountResult() {
        int count = 30;
        setup(count);
        _executor.executeByCount();
        FizzBuzzValue value = getValue(count);
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
    @Test
    public void studyListApi() {
        int count = 10;
        setup(count);
        _executor.executeByCount();
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
