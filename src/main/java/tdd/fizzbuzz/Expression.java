package tdd.fizzbuzz;

public interface Expression {
    FizzBuzzValue reduce();

    Expression times(Expression multiplier);

    Expression divide(Expression divisor);
}
