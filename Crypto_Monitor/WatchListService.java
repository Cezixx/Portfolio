import java.util.ArrayList;
import java.util.List;

public class WatchListService {
    private final List<CryptoRate> watchlist = new ArrayList<>();
    private final CryptoApiService apiService;

    public WatchListService(CryptoApiService apiService) {
        this.apiService = apiService;
    }

   public void addCrypto(String id, String symbol, String currency) {
        if (id == null || id.isBlank() || symbol == null || symbol.isBlank()) {
            System.out.println("Niepoprawne ID lub symbol kryptowaluty.");
            return;
        }

        String cleanId = id.trim().toLowerCase();
        String cleanSymbol = symbol.trim().toUpperCase();
        String cleanCurrency = (currency == null || currency.isBlank()) ? "PLN" : currency.trim().toUpperCase();

        for (CryptoRate rate : watchlist) {
            if (rate.getId().equalsIgnoreCase(cleanId) || rate.getSymbol().equalsIgnoreCase(cleanSymbol)) {
                System.out.println("Ta kryptowaluta jest już na liście obserwowanych.");
                return;
            }
        }

        CryptoRate fetched = apiService.getCryptoRate(cleanId);
        if (fetched != null) {
            CryptoRate entry = new CryptoRate(cleanId, cleanSymbol, fetched.getPrice(), cleanCurrency);
            watchlist.add(entry);
            System.out.println("Dodano do obserwowanych: " + entry);
        } else {
            System.out.println("Nie udało się pobrać kursu dla: " + cleanId);
        }
    }

    public void removeCrypto(String symbolOrId) {
        if (symbolOrId == null || symbolOrId.isBlank()) {
            System.out.println("Podaj symbol lub ID kryptowaluty.");
            return;
        }

        String target = symbolOrId.trim();
        boolean removed = watchlist.removeIf(
                rate -> rate.getId().equalsIgnoreCase(target) || rate.getSymbol().equalsIgnoreCase(target)
        );

        if (removed) {
            System.out.println("Usunięto z listy obserwowanych: " + target);
        } else {
            System.out.println("Nie znaleziono kryptowaluty: " + target);
        }
    }

    public void refreshAll() {
        if (watchlist.isEmpty()) {
            System.out.println("Lista obserwowanych jest pusta — brak kursów do odświeżenia.");
            return;
        }

        for (CryptoRate rate : watchlist) {
            CryptoRate updated = apiService.getCryptoRate(rate.getId());
            if (updated != null) {
                rate.setPrice(updated.getPrice());
            } else {
                System.out.println("Nie udało się odświeżyć kursu dla: " + rate.getSymbol());
            }
        }
        System.out.println("Odświeżono kursy.");
    }

    public void displayWatchList() {
        if (watchlist.isEmpty()) {
            System.out.println("Lista obserwowanych jest pusta.");
            return;
        }

        System.out.println("\n--- OBSERWOWANE KRYPTOWALUTY ---");
        for (CryptoRate rate : watchlist) {
            System.out.println("- " + rate);
        }
    }
}
