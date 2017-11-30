package tdd.fizzbuzz;

public class FizzBuzz extends FizzBuzzValue {
    public FizzBuzz(int number) {
        _number = number;
        _value = "FizzBuzz";
    }

    @Override
    String execute() {
        return _value;
    }
}
