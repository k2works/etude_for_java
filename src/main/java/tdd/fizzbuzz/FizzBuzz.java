package tdd.fizzbuzz;

public class FizzBuzz extends Value {

    public static final String FIZZ_BUZZ = "FizzBuzz";

    public FizzBuzz(int number) {
        _number = number;
        _value = FIZZ_BUZZ;
    }

    @Override
    String execute() {
        return _value;
    }
}
