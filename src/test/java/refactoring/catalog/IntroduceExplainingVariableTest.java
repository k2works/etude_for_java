package refactoring.catalog;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class IntroduceExplainingVariableTest {
    @Test
    public void priceBasePricePlusShipping() {
        Double expected = 110.0;
        IntroduceExplainingVariable introduceExplainingVariable = new IntroduceExplainingVariable(1, 100);
        Double result = introduceExplainingVariable.price();
        assertEquals(expected, result);
    }
    @Test
    public void priceBasePriceMinusDiscountPlusShipping() {
        Double expected = 50195.0;
        IntroduceExplainingVariable introduceExplainingVariable = new IntroduceExplainingVariable(501, 100);
        Double result = introduceExplainingVariable.price();
        assertEquals(expected, result);
    }
    @Test
    public void priceBasePriceMinusDiscountPlusNotMinShipping() {
        Double expected = 108.9;
        IntroduceExplainingVariable introduceExplainingVariable = new IntroduceExplainingVariable(3, 33);
        Double result = introduceExplainingVariable.price();
        assertEquals(expected, result);
    }
}
