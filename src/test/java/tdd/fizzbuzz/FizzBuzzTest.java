package tdd.fizzbuzz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzTest {
    @Test
    public void printFizz() {
        String result = FizzBuzz.execute(3);
        assertEquals("Fizz",result);
    }
    @Test
    public void notPrintFizz() {
        String result = FizzBuzz.execute(4);
        assertNotEquals("Fizz",result);
    }
    @Test
    public void printBuzz() {
        String result = FizzBuzz.execute(5);
        assertEquals("Buzz",result);
    }
    @Test
    public void notPrintBuzz() {
        String result = FizzBuzz.execute(6);
        assertNotEquals("Buzz",result);
    }
    @Test
    public void printFizzBuzz() {
        String result = FizzBuzz.execute(15);
        assertEquals("FizzBuzz",result);
    }
    @Test
    public void notPrintFizzBuzz() {
        String result = FizzBuzz.execute(20);
        assertNotEquals("FizzBuzz",result);
    }
    @Test
    public void printNotSatisfyTheCondition() {
        String result = FizzBuzz.execute(1);
        assertEquals("1",result);
    }
    @Test
    public void print100thCountResult() {
        FizzBuzz.executeByCount(100);
        assertEquals("Buzz",FizzBuzz.results[100]);
    }
    @Test
    public void print30thCountResult() {
        FizzBuzz.executeByCount(30);
        assertEquals("FizzBuzz",FizzBuzz.results[30]);
    }
}
