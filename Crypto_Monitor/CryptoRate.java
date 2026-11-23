public class CryptoRate {
    private final String id;
    private String symbol;
    private double price;
    private String currency;

    public CryptoRate(String id, String symbol, double price, String currency) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.currency = currency;
    }

    public String getId() {
        return this.id;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return symbol + " ("+ id + "): " + String.format("%.2f", price) + " " + currency;
    }
}
