package tdd.fizzbuzz;

public class FizzBuzzValueProduct implements Expression {
    Expression _multiplicand;
    Expression _multiplier;

    FizzBuzzValueProduct(Expression multiplicand, Expression multiplier) {
        _multiplicand = multiplicand;
        _multiplier = multiplier;
    }
    public FizzBuzzValue reduce() {
        int number = _multiplicand.reduce()._number * _multiplier.reduce()._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }

    @Override
    public Expression times(Expression multiplier) {
        return new FizzBuzzValueProduct(this, multiplier);
    }
}
