import java.util.ArrayList;
import java.util.List;

public class CartService {
       private final List<CartItem> items = new ArrayList<>();
       
       public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Niepoprawny produkt lub ilość.");
            return;
        }
        for (CartItem item : items) {
            if(item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Zwiękoszono ilość produktu \"" + product.getName() + "\" w koszyku.");
            }
        }

        items.add(new CartItem(product, quantity));
        System.out.println("Dodano do koszyka: \"" + product.getName() + "\" x" + quantity);
       }

       public void removeProduct(String productId) {
            boolean removed = items.removeIf(item -> item.getProduct().getId().equals(productId));

            if (removed) {
                System.out.println("Usunięto produkt z koszyka.");
            }else {
                System.out.println("Nie znaleziono produktu o ID: " + productId);
            }
       }

       public void updateQuantity(String productId, int newQuantity) {
        for(int i = 0; i < items.size(); i++){
            CartItem item = items.get(i);
            if(item.getProduct().getId().equals(productId)) {
                if(newQuantity <= 0) {
                    items.remove(i);
                    System.out.println("Usunięto produkt z koszyka (ilość  mniejsa od 0)");
                }else {
                    item.setQuantity(newQuantity);
                    System.out.println("Zaktualizowano ilość produktu \"" + item.getProduct().getName() + "\" na: " + newQuantity);
                }
                return;
            }
        }
        System.out.println("Nie znalezuiono produktu o ID: " + productId);
       }

       public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
        total += item.getTotalprice(); 
        }
        return total;
       }

       public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Brak produktów w koszyku.");
            return;
        }
        System.out.println("\n--- ZAWARTOŚĆ KOSZYKA ---");
        for (CartItem item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Razem do zapłaty: " + String.format("%.2f", getTotalPrice()) + "zł");
       }
}
