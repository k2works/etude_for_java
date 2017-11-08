package tdd.money;

class Sum implements Expression {
    Expression augend;
    Expression addend;
    Sum(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }
    public Expression plus(Expression added) {
        return null;
    }
    public Money reduce(Bank bank, String to) {
        int amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount;
        return new Money(amount, to);
    }
}
