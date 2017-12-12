  
  
# リファクタリングカタログ
  
  
## メソッドの構成
  
### メソッドの抽出
  
#### 設計
  
##### クラス図
  

![](./assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a0.png?0.06376198229075403)  
##### シーケンス図
  

![](./assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a1.png?0.12268637426174811)  
#### 実装
  
`ExtractMethodTest.java`
```java
package refactoring.catalog;
  
import org.junit.jupiter.api.Test;
  
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
  
import static org.junit.Assert.assertEquals;
  
public class ExtractMethodTest {
    @Test
    public void printBanner() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "***********************\n" +
                "**** Customer Owes ****\n" +
                "***********************\n" +
                "name:Test\n" +
                "amount:0.0\n";
  
        ExtractMethod extractMethod = new ExtractMethod();
        extractMethod.printOwing();
        assertEquals(expected, outContent.toString());
    }
    @Test void printBannerWithArg() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "***********************\n" +
                "**** Customer Owes ****\n" +
                "***********************\n" +
                "name:Test\n" +
                "amount:12.0\n";
  
        ExtractMethod extractMethod = new ExtractMethod();
        double previousAmount = 10;
        extractMethod.printOwing(previousAmount);
        assertEquals(expected, outContent.toString());
    }
}
  
```  
`ExtractMethod.java`
```java
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
  
```  
`Order.java`
```java
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
  
```  
  
  
### 問い合わせによる一時変数の置き換え
  
#### 設計
  
##### クラス図
  

![](./assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a2.png?0.6180157001961004)  
##### シーケンス図
  

![](./assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a3.png?0.2574227690617512)  
  
#### 実装
  
`ReplaceTempWithQueryTest.java`
```java
package refactoring.catalog;
  
import org.junit.jupiter.api.Test;
  
import static org.junit.Assert.assertEquals;
  
public class ReplaceTempWithQueryTest {
    @Test
    public void test98PercentDiscountPrice() {
        ReplaceTempWithQuery replaceTempWithQuery = new ReplaceTempWithQuery(1000, 1);
        Double result = replaceTempWithQuery.getPrice();
        Double expected = 980d;
        assertEquals(expected, result);
    }
    @Test
    public void test95PercentDiscountPrice() {
        ReplaceTempWithQuery replaceTempWithQuery = new ReplaceTempWithQuery(1000, 2);
        Double result = replaceTempWithQuery.getPrice();
        Double expected = 1900d;
        assertEquals(expected, result);
    }
}
  
```  
`ReplaceTempWithQuery.java`
```java
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
  
```  
`Order.java`
```java
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
  
```  
  
  
## オブジェクト間での特性の移動
  
## データの再編成
  
## 条件記述の単純化
  
## メソッド呼び出しの単純化
  
## 継承の取り扱い
  
## 大きなリファクタリング
  
  