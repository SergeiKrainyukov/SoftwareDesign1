### Было

```java
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
    
    public List<Product> getProducts(){
        return products;
    }
}
```

### Стало

```java
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product product)) return false;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}

public final class ShoppingCart {
    private final List<Product> products;

    private ShoppingCart(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public ShoppingCart addProduct(Product product) {
        List<Product> newProducts = new ArrayList<>(this.products);
        newProducts.add(product);
        return new ShoppingCart(newProducts);
    }

    public ShoppingCart removeProduct(Product product) {
        List<Product> newProducts = new ArrayList<>(this.products);
        newProducts.remove(product);
        return new ShoppingCart(newProducts);
    }

    public double calculateTotal() {
        return products.stream()
                       .mapToDouble(Product::getPrice)
                       .sum();
    }

    public List<Product> getProducts(){
        return products;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ShoppingCart cart)) return false;
        return Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
```