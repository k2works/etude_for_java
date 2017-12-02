package tdd.fizzbuzz;

public class FizzBuzzValueQuotient implements Expression {
    Expression _dividend;
    Expression _divisor;

    FizzBuzzValueQuotient(Expression dividend, Expression divisor) {
        _dividend = dividend;
        _divisor = divisor;
    }

    @Override
    public FizzBuzzValue reduce() {
        int number = _dividend.reduce()._number / _divisor.reduce()._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }

    @Override
    public Expression times(Expression multiplier) {
        return null;
    }

    @Override
    public Expression divide(Expression divisor) {
        return new FizzBuzzValueQuotient(this, divisor);
    }
}
