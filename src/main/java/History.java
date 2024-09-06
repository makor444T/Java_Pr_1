import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class History {
    private List<Order> orderHistory = new ArrayList<>();

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("Історія замовлень порожня.");
        } else {
            for (Order order : orderHistory) {
                System.out.println(order);
                System.out.println("----------");
            }
        }
    }
}
