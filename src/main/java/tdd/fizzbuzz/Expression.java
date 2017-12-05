package tdd.fizzbuzz;

public interface Expression {
    Value reduce();

    Expression times(Expression multiplier);

    Expression divide(Expression divisor);
}
