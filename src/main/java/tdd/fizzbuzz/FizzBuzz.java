package tdd.fizzbuzz;

public class FizzBuzz {

    public static String[] results;

    public static String execute(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";
        } else if (number % 5 == 0) {
            return "Buzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else {
            return String.valueOf(number);
        }
    }

    public static void executeByCount(int count) {
        results = new String[count + 1];
        for (int i = 0; i <= count; ++i) {
            results[i] = FizzBuzz.execute(i);
        }
    }
}
