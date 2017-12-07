package refactoring.catalog;

import java.util.ArrayList;
import java.util.List;

class Order {
    public double getAmount() {
        return 0;
    }
}

class ExtractMethod {
    private String _name;
    private List<Order> _orders;

    public ExtractMethod() {
        _name = "Test";
        _orders = new ArrayList();
        _orders.add(new Order());
    }

    public void printOwing() {
        double outstanding = 0.0;

        printBanner();

        // 未払い金の計算
        for(Order _order : _orders)
            outstanding += _order.getAmount();

        // 明細の印刷
        System.out.println("name:" + _name);
        System.out.println("amount:" + outstanding);
    }

    private void printBanner() {
        // バナーの印刷
        System.out.println("***********************");
        System.out.println("**** Customer Owes ****");
        System.out.println("***********************");
    }
}
