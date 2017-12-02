package tdd.fizzbuzz;

abstract class FizzBuzzValue implements Expression {
    protected int _number;
    protected String _value;

    abstract String execute();

    static FizzBuzzValue makeFizzBuzzValue(int number) {
        if (number % 3 == 0 && number % 5 == 0) {
            return new FizzBuzz(number);
        } else if (number % 5 == 0) {
            return new Buzz(number);
        } else if (number % 3 == 0) {
            return new Fizz(number);
        } else {
            return new NullValue(number);
        }
    }

    public boolean equals(Object object) {
        FizzBuzzValue value = (FizzBuzzValue) object;
        return _number == value._number
                && _value.equals(value._value);
    }

    public String toString() {
        return _number + " " + _value;
    }

    public Expression times(FizzBuzzValue multiplier) {
        return new FizzBuzzValueProduct(this, multiplier);
    }

    public FizzBuzzValue reduce() {
        return this;
    }
}
