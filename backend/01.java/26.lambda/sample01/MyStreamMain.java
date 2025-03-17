
import java.util.stream.Stream;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

public class MyStreamMain {

    private final static List<Product> products = new ArrayList<>();

    public static void setProduct() {
        Product p1 = new Product(1, true, "fashion", 50000);
        Product p2 = new Product(2, true, "fashion", 38000);
        Product p3 = new Product(3, true, "it", 250000);
        Product p4 = new Product(4, true, "it", 85000);
        Product p5 = new Product(5, true, "it", 99000);
        Product p6 = new Product(6, true, "it", 75000);
        Product p7 = new Product(7, true, "furniture", 350000);
        Product p8 = new Product(8, false, "furniture", 210000);
        Product p9 = new Product(9, false, "furniture", 58000);
        Product p10 = new Product(10, false, "it", 120000);

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p6);
        products.add(p7);
        products.add(p8);
        products.add(p9);
        products.add(p10);
    
    }

    // Java 8 이후
    // Stream API를 사용하여 필터링
    public static void myfilterJava8() {
        Stream<Product> stream = products.stream();
        long cnt = stream.filter(p -> p.isUsable())
                         .filter(p -> p.getPrice() <= 100000)
                         .count();
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        setProduct();

        // Stream API 사용
        myfilterJava8();
    }
    
}
