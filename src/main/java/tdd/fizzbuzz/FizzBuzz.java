package tdd.fizzbuzz;

public class FizzBuzz {

    public static String execute(int number) {
        if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0){
            return "Buzz";
        } else {
            return null;
        }
    }
}
