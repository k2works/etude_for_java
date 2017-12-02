package tdd.fizzbuzz;

public class FizzBuzzValueProduct implements Expression {
    FizzBuzzValue _multiplicand;
    FizzBuzzValue _multiplier;

    FizzBuzzValueProduct(FizzBuzzValue multiplicand, FizzBuzzValue multiplier) {
        _multiplicand = multiplicand;
        _multiplier = multiplier;
    }
    public FizzBuzzValue reduce() {
        int number = _multiplicand._number * _multiplier._number;
        return FizzBuzzValue.makeFizzBuzzValue(number);
    }
}
