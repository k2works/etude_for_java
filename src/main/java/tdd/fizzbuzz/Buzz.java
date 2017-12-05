package tdd.fizzbuzz;

public class Buzz extends Value {

    public static final String BUZZ = "Buzz";

    public Buzz(int number) {
        _number = number;
        _value = BUZZ;
    }

    @Override
    String execute() {
        return _value;
    }
}
