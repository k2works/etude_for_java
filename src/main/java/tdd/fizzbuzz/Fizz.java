package tdd.fizzbuzz;

public class Fizz extends Value {

    public static final String FIZZ = "Fizz";

    public Fizz(int number) {
        _number = number;
        _value = FIZZ;
    }

    @Override
    String execute() {
        return _value;
    }
}
