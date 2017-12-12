package refactoring.catalog;

public class ReplaceTempWithQuery {
    private Order _order;
    ReplaceTempWithQuery(int quantity, int itemPrice) {
        _order = new Order(quantity, itemPrice);
    }

    double getPrice() {
        return _order.getPrice();
    }
}
