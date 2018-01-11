package refactoring.catalog;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class SplitTemporaryVariableTest {
    @Test
    public void testGetDistanceTravelled() {
        Double expected = 0d;
        SplitTemporaryVariable splitTemporaryVariable = new SplitTemporaryVariable(1, 1, 1, 1);
        Double result = splitTemporaryVariable.getDistanceTravelled(0);
        assertEquals(expected, result);
    }
}
