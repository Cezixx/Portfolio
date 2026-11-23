public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = Math.max(1, quantity);
    }
    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >=0) {
            this.quantity = quantity;
        }
    }

    public double getTotalprice() {
       return this.product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return product.getName() + " x " +  quantity + " = " + String.format("%.2f", getTotalprice()) + "zł" ;
    }
}