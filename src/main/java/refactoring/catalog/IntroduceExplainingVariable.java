package refactoring.catalog;

public class IntroduceExplainingVariable {
    private final int _quantity;
    private final int _itemPrice;

    IntroduceExplainingVariable(int quantity, int itemPrice) {
        _quantity = quantity;
        _itemPrice = itemPrice;
    }
    public Double price() {
        Order order = new Order(_quantity,_itemPrice);
        return order.price();
    }
}
