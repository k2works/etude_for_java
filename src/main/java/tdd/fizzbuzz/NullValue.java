package tdd.fizzbuzz;

public class NullValue extends Value {
    public NullValue(int number) {
        _number = number;
        _value = String.valueOf(number);
    }
    @Override
    String execute() {
        return _value;
    }
}
