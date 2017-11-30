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

            if (i % 3 == 0 && i % 5 == 0) {
                value = new FizzBuzz();
            } else if (i % 5 == 0) {
                value = new Buzz();
            } else if (i % 3 == 0) {
                value = new Fizz();
            } else {
                value = null;
            }

            if (value == null) {
                results[i] = String.valueOf(i);
            } else {
                results[i] = value.execute();
            }
        }
    }
}
