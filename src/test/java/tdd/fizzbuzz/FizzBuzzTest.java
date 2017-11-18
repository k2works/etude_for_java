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
