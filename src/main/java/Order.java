import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private List<Product> products;
    private double totalPrice;
    private String status = "Нове";

    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Замовлення:\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Загальна вартість: ").append(totalPrice).append("\n");
        sb.append("Статус: ").append(status);
        return sb.toString();
    }
}
