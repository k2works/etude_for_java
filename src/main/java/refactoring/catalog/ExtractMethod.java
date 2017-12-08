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
        printBanner();
        double outstanding = getOutstanding();
        printDetails(outstanding);
    }

    public void printOwing(double previousAmount) {
        double outstanding = previousAmount * 1.2;

        printBanner();

        for(Order order : _orders)
            outstanding += order.getAmount();

        printDetails(outstanding);
    }

    private double getOutstanding() {
        double result = 0.0;
        // 未払い金の計算
        for(Order _order : _orders)
            result += _order.getAmount();
        return result;
    }

    private void printDetails(double outstanding) {
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
