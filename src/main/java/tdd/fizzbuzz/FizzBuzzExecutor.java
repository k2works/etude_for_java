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
