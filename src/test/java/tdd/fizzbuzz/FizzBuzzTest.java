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
}
