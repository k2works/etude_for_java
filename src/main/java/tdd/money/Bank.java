package tdd.money;

class Bank {
    Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    void addRate(String chf, String usd, int i) {
    }
}
