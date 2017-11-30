package tdd.fizzbuzz;

abstract class FizzBuzzValue {
    private int _number;
    private String _value;

    abstract String execute();

    static FizzBuzzValue makeFizzBuzzValue(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return new FizzBuzz(number);
        } else if (number % 5 == 0) {
            return new Buzz(number);
        } else if (number % 3 == 0) {
            return new Fizz(number);
        } else {
            return null;
        }
    }
}
