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
        int basePrice = _quantity * _itemPrice;
        double discountFactor;
        if (basePrice > 1000) discountFactor = 0.95;
        else discountFactor = 0.98;
        return basePrice * discountFactor;
    }
}
