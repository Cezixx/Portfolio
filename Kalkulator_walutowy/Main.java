import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyService currencyService = new CurrencyService();

        System.out.println("==== KALKULATOR WALUT NBP ===");

        while(true) {
            System.out.println("\nWybierz opcję:");
            System.out.println("1. Przelicz waluitę obcą na PLN");
            System.out.println("2. Przelicz z PLN na walutę obcą");
            System.out.println("3. Wyjście");
            System.out.println("Twój wybór (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 3) {
                System.out.println("Do widzenia!");
                break;
            }

            if (choice != 1 && choice != 2) {
                System.out.println("Niepoprawna opcja. Wybierz 1, 2 lub 3.");
                continue;
            }

            System.out.println("Podaj kod waluty (np. EUR, USD, GBP, CHF): ");
            String currencyCode = scanner.nextLine().trim().toUpperCase();

            System.out.println("Podaj kwotę: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            Currency currency = currencyService.getCurrencyRate(currencyCode);

            if (currency == null) {
                System.out.println("Nie udało się pobrać kursu dla waluty: " + currencyCode);
                break;
            }

            double rate = currency.getRate();

            if (choice == 1) {
                double result = amount * rate;
                System.out.printf("Wynik: %.2f %s = %.2f PLN (Kurs: %.4f)\n", amount, currencyCode, result, rate);
            
            }else {
                double result = amount / rate;
                System.out.printf("Wynik: %.2f PLN = %.2f %s (Kurs: %.4f)\n", amount, result, currencyCode, rate);
            }
        }
        scanner.close();
    }
} 