import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення об'єктів класу Product
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        Cart cart = new Cart();
        History history = new History();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти!");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(product1);
                    System.out.println(product2);
                    System.out.println(product3);
                    break;
                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int id = scanner.nextInt();
                    if (id == 1) cart.addProduct(product1);
                    else if (id == 2) cart.addProduct(product2);
                    else if (id == 3) cart.addProduct(product3);
                    else System.out.println("Товар з таким ID не знайдено");
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товар перед видаленням.");
                    } else {
                        System.out.println("Введіть ID товару для видалення з кошика:");
                        int ids = scanner.nextInt();
                        Product productToRemove = findProductById(ids, product1, product2, product3);
                        if (productToRemove != null) {
                            cart.removeProduct(productToRemove);
                            System.out.println("Товар успішно видалено з кошика");
                        } else {
                            System.out.println("Товар з таким ID не знайдено");
                        }
                    }
                    break;
                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        history.addOrder(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 6:
                    history.viewOrderHistory();
                    break;
                case 7:
                    System.out.println("Введіть назву або категорію товару для пошуку:");
                    scanner.nextLine();
                    String query = scanner.nextLine();
                    searchProducts(query, product1, product2, product3);
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }

    // Метод пошуку товару за назвою або категорією
    private static void searchProducts(String query, Product... products) {
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(query.toLowerCase()) ||
                    product.getCategory().getName().toLowerCase().contains(query.toLowerCase())) {
                System.out.println(product);
            }
        }
    }

    // Метод для пошуку товару за ID
    private static Product findProductById(int id, Product... products) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
