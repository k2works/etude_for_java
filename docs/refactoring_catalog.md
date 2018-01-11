  
  
# リファクタリングカタログ
  
  
## メソッドの構成
  
### メソッドの抽出
  
#### 設計
  
##### クラス図
  

![](../assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a0.png?0.9640066979539443)  
##### シーケンス図
  

![](../assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a1.png?0.923981827299106)  
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
  
```  
  
  
### 問い合わせによる一時変数の置き換え
  
#### 設計
  
##### クラス図
  

![](../assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a2.png?0.8793608491487248)  
##### シーケンス図
  

![](../assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a3.png?0.12641640659082154)  
  
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
  
```  
  
### 説明用変数の導入
  
#### 設計
  
##### クラス図
  

![](../assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a4.png?0.5763193611242214)  
##### シーケンス図
  

![](../assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a5.png?0.1574717280107487)  
  
#### 実装
  
`IntroduceExplainingVariableTest.java`
```java
package refactoring.catalog;
  
import org.junit.jupiter.api.Test;
  
import static org.junit.Assert.assertEquals;
  
public class IntroduceExplainingVariableTest {
    @Test
    public void priceBasePricePlusShipping() {
        Double expected = 110.0;
        IntroduceExplainingVariable introduceExplainingVariable = new IntroduceExplainingVariable(1, 100);
        Double result = introduceExplainingVariable.price();
        assertEquals(expected, result);
    }
    @Test
    public void priceBasePriceMinusDiscountPlusShipping() {
        Double expected = 50195.0;
        IntroduceExplainingVariable introduceExplainingVariable = new IntroduceExplainingVariable(501, 100);
        Double result = introduceExplainingVariable.price();
        assertEquals(expected, result);
    }
    @Test
    public void priceBasePriceMinusDiscountPlusNotMinShipping() {
        Double expected = 108.9;
        IntroduceExplainingVariable introduceExplainingVariable = new IntroduceExplainingVariable(3, 33);
        Double result = introduceExplainingVariable.price();
        assertEquals(expected, result);
    }
}
  
```  
`IntroduceExplainingVariable.java`
```java
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
  
```  
`Order.java`
```java
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
  
```  
  
### 一時変数の分離
  
#### 設計
  
##### クラス図
  

![](../assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a6.png?0.09178574547908003)  
##### シーケンス図
  
  
#### 実装
  
`SplitTemporaryVariableTest.java`
```java
package refactoring.catalog;
  
import org.junit.jupiter.api.Test;
  
import static org.junit.Assert.assertEquals;
  
public class SplitTemporaryVariableTest {
    @Test
    public void testGetDistanceTravelled() {
        Double expected = 0d;
        SplitTemporaryVariable splitTemporaryVariable = new SplitTemporaryVariable(1, 1, 1, 1);
        Double result = splitTemporaryVariable.getDistanceTravelled(0);
        assertEquals(expected, result);
    }
}
  
```  
`SplitTemporaryVariable.java`
```java
package refactoring.catalog;
  
public class SplitTemporaryVariable {
    private double _primaryForce;
    private double _secondaryForce;
    private double _mass;
    private int _delay;
  
    SplitTemporaryVariable(double primaryForce, double secondaryForce, double mass, int delay) {
        _primaryForce = primaryForce;
        _secondaryForce = secondaryForce;
        _mass = mass;
        _delay = delay;
    }
  
    double getDistanceTravelled(int time) {
        double result;
        double acc = _primaryForce / _mass;
        int primaryTime = Math.min(time, _delay);
        result = 0.5 * acc * primaryTime * primaryTime;
        int secondaryTime = time - _delay;
        if (secondaryTime > 0) {
            double primaryVel = acc * _delay;
            acc = (_primaryForce + _secondaryForce) / _mass;
            result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }
}
  
```  
  
## オブジェクト間での特性の移動
  
## データの再編成
  
## 条件記述の単純化
  
## メソッド呼び出しの単純化
  
## 継承の取り扱い
  
## 大きなリファクタリング
  
  