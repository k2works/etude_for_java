package refactoring.catalog;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ReplaceTempWithQueryTest {
    @Test
    public void test98PercentDiscountPrice() {
        ReplaceTempWithQuery replaceTempWithQuery = new ReplaceTempWithQuery(1000, 1);
        Double result = replaceTempWithQuery.getPrice();
        Double expected = 980d;
        assertEquals(expected, result);
    }
    @Test
    public void test95PercentDiscountPrice() {
        ReplaceTempWithQuery replaceTempWithQuery = new ReplaceTempWithQuery(1000, 2);
        Double result = replaceTempWithQuery.getPrice();
        Double expected = 1900d;
        assertEquals(expected, result);
    }
}
