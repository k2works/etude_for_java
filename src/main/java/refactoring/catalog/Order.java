package refactoring.catalog;

public class Order {
    private int _quantity;
    private int _itemPrice;

    Order() {
    }

    Order(int quantity, int itemPrice) {
        _quantity = quantity;
        _itemPrice = itemPrice;
    }

    public double getAmount() {
        return 0;
    }

    double getPrice() {
        final double discountFactor;
        discountFactor = discountFactor();
        return basePrice() * discountFactor;
    }

    private double discountFactor() {
        if (basePrice() > 1000) return 0.95;
        else return 0.98;
    }

    private int basePrice() {
        return _quantity * _itemPrice;
    }
}
