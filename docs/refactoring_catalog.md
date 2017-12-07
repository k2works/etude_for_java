  
  
# リファクタリングカタログ
  
  
## メソッドの構成
  
### メソッドの抽出
  
#### 設計
  
##### クラス図
  

![](./assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a0.png?0.5216419387048097)  
##### シーケンス図
  

![](./assets/refactoring_catalog/304c6a7221fb9ead610ce99af5b2958a1.png?0.012318845162063052)  
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
}
  
```  
`ExtractMethod.java`
```java
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
  
```  
  
## オブジェクト間での特性の移動
  
## データの再編成
  
## 条件記述の単純化
  
## メソッド呼び出しの単純化
  
## 継承の取り扱い
  
## 大きなリファクタリング
  
  