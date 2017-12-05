package tdd.fizzbuzz;

public class Accumulate implements Expression {
    Expression _accumulated;
    Expression _accumulate;

    Accumulate(Expression accumulated, Expression accumulate) {
        _accumulated = accumulated;
        _accumulate = accumulate;
    }

    @Override
    public Expression times(Expression multiplier) {
        return new Accumulate(this, multiplier);
    }

    @Override
    public Expression divide(Expression divisor) {
        return new Accumulate(_accumulated.divide(divisor), _accumulate.divide(divisor));
    }

    @Override
    public Value reduce() {
        int number = _accumulated.reduce()._number * _accumulate.reduce()._number;
        return Value.makeFizzBuzzValue(number);
    }
}
