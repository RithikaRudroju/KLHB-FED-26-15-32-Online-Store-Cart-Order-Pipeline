import java.util.Scanner;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void display() {
        System.out.println(id + "  " + name + "  Rs." + price);
    }
}

class Cart {
    Product[] products = new Product[10];
    int[] quantity = new int[10];
    int count = 0;

    void addProduct(Product p, int qty) {
        products[count] = p;
        quantity[count] = qty;
        count++;

        System.out.println("Product added to cart.");
    }

    void removeProduct(int id) {
        for (int i = 0; i < count; i++) {

            if (products[i].id == id) {

                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                    quantity[j] = quantity[j + 1];
                }

                count--;

                System.out.println("Product removed from cart.");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    void displayCart() {

        if (count == 0) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n----- SHOPPING CART -----");

        for (int i = 0; i < count; i++) {

            double amount =
                    products[i].price * quantity[i];

            System.out.println(
                    products[i].name +
                    " | Quantity: " + quantity[i] +
                    " | Amount: Rs." + amount
            );
        }
    }

    double calculateBill() {

        double total = 0;

        for (int i = 0; i < count; i++) {
            total = total +
                    products[i].price * quantity[i];
        }

        return total;
    }
}

public class OnlineStorePart1 {

    static Product findProduct(Product[] products, int id) {

        for (Product p : products) {

            if (p.id == id) {
                return p;
            }
        }

        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Product[] catalogue = {

            new Product(1, "Laptop", 50000),
            new Product(2, "Mobile", 20000),
            new Product(3, "Headphones", 2000),
            new Product(4, "Keyboard", 1500),
            new Product(5, "Mouse", 800)
        };

        Cart cart = new Cart();

        int choice;

        do {

            System.out.println("\n===== ONLINE STORE =====");
            System.out.println("1. View Catalogue");
            System.out.println("2. Add Product");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Product");
            System.out.println("5. Calculate Bill");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\n----- PRODUCT CATALOGUE -----");

                    for (Product p : catalogue) {
                        p.display();
                    }

                    break;

                case 2:

                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();

                    Product p = findProduct(catalogue, id);

                    if (p != null) {

                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();

                        if (qty > 0) {
                            cart.addProduct(p, qty);
                        } else {
                            System.out.println("Invalid quantity.");
                        }

                    } else {
                        System.out.println("Product not found.");
                    }

                    break;

                case 3:
                    cart.displayCart();
                    break;

                case 4:

                    System.out.print("Enter Product ID: ");
                    int removeId = sc.nextInt();

                    cart.removeProduct(removeId);
                    break;

                case 5:

                    double total = cart.calculateBill();

                    System.out.println(
                            "Total Bill = Rs." + total
                    );

                    break;

                case 6:
                    System.out.println("Exiting Part 1...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}