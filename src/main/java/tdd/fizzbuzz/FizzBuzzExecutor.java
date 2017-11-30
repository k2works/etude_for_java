package tdd.fizzbuzz;

public class FizzBuzzExecutor {
    private static String[] results;

    public static String[] getResults() {
        return results;
    }

    public static void executeByCount(int count) {
        FizzBuzzValue value;
        results = new String[count + 1];
        for (int i = 0; i <= count; ++i) {
            value = FizzBuzzValue.makeFizzBuzzValue(i);
            if (value == null) {
                results[i] = String.valueOf(i);
            } else {
                results[i] = value.execute();
            }
        }
    }
}
