import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CryptoApiService apiService = new CryptoApiService();
        WatchListService watchListService = new WatchListService(apiService);

        boolean running = true;

        while (running) {
            System.out.println("\n=== MONITOR KRYPTOWALUT ===");
            System.out.println("1. Dodaj kryptowalutę do obserwowanych");
            System.out.println("2. Usuń kryptowalutę z obserwowanych");
            System.out.println("3. Odśwież wszystkie kursy");
            System.out.println("4. Pokaż listę obserwowanych");
            System.out.println("5. Wyjdź");
            System.out.print("Wybierz opcję (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Podaj ID kryptowaluty w API (np. bitcoin, ethereum, solana): ");
                    String id = scanner.nextLine().trim().toLowerCase();

                    System.out.print("Podaj symbol (np. BTC, ETH, SOL): ");
                    String symbol = scanner.nextLine().trim().toUpperCase();

                    System.out.print("Podaj walutę (domyślnie PLN): ");
                    String currency = scanner.nextLine().trim().toUpperCase();
                    if (currency.isEmpty()) {
                        currency = "PLN";
                    }

                    watchListService.addCrypto(id, symbol, currency);
                    break;

                case "2":
                    watchListService.displayWatchList();
                    System.out.print("Podaj symbol lub ID do usunięcia: ");
                    String removeTarget = scanner.nextLine().trim();
                    watchListService.removeCrypto(removeTarget);
                    break;

                case "3":
                    watchListService.refreshAll();
                    break;

                case "4":
                    watchListService.displayWatchList();
                    break;

                case "5":
                    running = false;
                    System.out.println("Zamykanie monitora kryptowalut. Do zobaczenia!");
                    break;

                default:
                    System.out.println("Niepoprawny wybór. Wybierz opcję od 1 do 5.");
            }
        }

        scanner.close();
    }
}