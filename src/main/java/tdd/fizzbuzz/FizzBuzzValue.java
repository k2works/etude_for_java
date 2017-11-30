package tdd.fizzbuzz;

abstract class FizzBuzzValue {
    abstract String execute();

    static FizzBuzzValue makeFizzBuzzValue(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return new FizzBuzz();
        } else if (number % 5 == 0) {
            return new Buzz();
        } else if (number % 3 == 0) {
            return new Fizz();
        } else {
            return null;
        }
    }
}
