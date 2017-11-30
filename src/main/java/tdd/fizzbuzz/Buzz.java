package tdd.fizzbuzz;

public class Buzz extends FizzBuzzValue {
    public Buzz(int number) {
        _number = number;
        _value = "Buzz";
    }

    @Override
    String execute() {
        return _value;
    }
}
