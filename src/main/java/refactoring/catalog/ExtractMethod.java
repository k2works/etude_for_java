package refactoring.catalog;

import java.util.ArrayList;
import java.util.List;

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
        printBanner();
        double outstanding = getOutstanding(previousAmount * 1.2);

        printDetails(outstanding);
    }

    private double getOutstanding(double previousAmount) {
        double outstanding = previousAmount;
        for (Order order : _orders)
            outstanding += order.getAmount();
        return outstanding;
    }

    private double getOutstanding() {
        double result = getOutstanding(0.0);
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
