package refactoring.catalog;

import javax.swing.plaf.metal.MetalTheme;

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

    public double price() {
        // 価格(price)は、基本価格(base price) - 数量割引(quantity discount) + 送料(shipping)
        return _quantity * _itemPrice -
                Math.max(0, _quantity - 500) * _itemPrice * 0.05 +
                Math.min(_quantity * _itemPrice * 0.1, 100.0);
    }
}
