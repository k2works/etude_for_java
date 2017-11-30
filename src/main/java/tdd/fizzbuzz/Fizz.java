package tdd.fizzbuzz;

public class Fizz extends FizzBuzzValue {
    public Fizz(int number) {
        _number = number;
        _value = "Fizz";
    }

    @Override
    String execute() {
        return _value;
    }
}
