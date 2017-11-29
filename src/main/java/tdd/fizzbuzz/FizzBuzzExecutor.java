package tdd.fizzbuzz;

public class FizzBuzzExecutor {
    private static String[] results;

    public static String[] getResults() {
        return results;
    }

    public static void executeByCount(int count) {
        results = new String[count + 1];
        for (int i = 0; i <= count; ++i) {
            results[i] = FizzBuzz.execute(i);
        }
    }
}
