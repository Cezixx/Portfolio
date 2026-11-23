import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CartService cartService = new CartService();

        // Przykładowy katalog produktów dostępnych w sklepie
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product("1", "Klawiatura mechaniczna", 299.99));
        catalog.add(new Product("2", "Mysz bezprzewodowa", 149.50));
        catalog.add(new Product("3", "Podkładka XXL", 59.00));
        catalog.add(new Product("4", "Kabel USB-C (2m)", 29.90));

        boolean running = true;

        while (running) {
            System.out.println("\n=== SKLEP — KOSZYK ZAKUPOWY ===");
            System.out.println("1. Pokaż katalog produktów");
            System.out.println("2. Dodaj produkt do koszyka");
            System.out.println("3. Zmień ilość produktu w koszyku");
            System.out.println("4. Usuń produkt z koszyka");
            System.out.println("5. Pokaż koszyk i podsumowanie");
            System.out.println("6. Wyjdź");
            System.out.print("Wybierz opcję (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayCatalog(catalog);
                    break;

                case "2":
                    displayCatalog(catalog);
                    System.out.print("Podaj ID produktu: ");
                    String addId = scanner.nextLine().trim();
                    Product selectedProduct = findProductById(catalog, addId);

                    if (selectedProduct == null) {
                        System.out.println("Nie znaleziono produktu o ID: " + addId);
                        break;
                    }

                    System.out.print("Podaj ilość: ");
                    int addQuantity = readInt(scanner);
                    cartService.addProduct(selectedProduct, addQuantity);
                    break;

                case "3":
                    cartService.displayCart();
                    System.out.print("Podaj ID produktu do zmiany ilości: ");
                    String updateId = scanner.nextLine().trim();
                    System.out.print("Podaj nową ilość (0 lub mniej usunie pozycję): ");
                    int newQuantity = readInt(scanner);
                    cartService.updateQuantity(updateId, newQuantity);
                    break;

                case "4":
                    cartService.displayCart();
                    System.out.print("Podaj ID produktu do usunięcia: ");
                    String removeId = scanner.nextLine().trim();
                    cartService.removeProduct(removeId);
                    break;

                case "5":
                    cartService.displayCart();
                    break;

                case "6":
                    running = false;
                    System.out.println("Dziękujemy za zakupy! Do zobaczenia.");
                    break;

                default:
                    System.out.println("Niepoprawny wybór. Wybierz opcję od 1 do 6.");
            }
        }

        scanner.close();
    }

    private static void displayCatalog(List<Product> catalog) {
        System.out.println("\n--- KATALOG PRODUKTÓW ---");
        for (Product product : catalog) {
            System.out.println(product);
        }
    }

    private static Product findProductById(List<Product> catalog, String id) {
        for (Product product : catalog) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Wpisz poprawną liczbę całkowitą: ");
            }
        }
    }
}