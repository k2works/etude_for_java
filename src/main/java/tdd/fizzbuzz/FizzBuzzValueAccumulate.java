package tdd.fizzbuzz;

public class FizzBuzzValueAccumulate implements Expression {
    Expression _accumulated;
    Expression _accumulate;

    FizzBuzzValueAccumulate(Expression accumulated, Expression accumulate) {
        _accumulated = accumulated;
        _accumulate = accumulate;
    }

    @Override
    public Expression times(Expression multiplier) {
        return new FizzBuzzValueAccumulate(this, multiplier);
    }

    @Override
    public Expression divide(Expression divisor) {
        return new FizzBuzzValueAccumulate(_accumulated.divide(divisor), _accumulate.divide(divisor));
    }

    @Override
    public FizzBuzzValue reduce() {
        int number = _accumulated.reduce()._number * _accumulate.reduce()._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }
}
