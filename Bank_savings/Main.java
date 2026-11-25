package Bank_savings;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SavingsAccount account = new SavingsAccount(
            "PL61 1090 1014 0000 0712 1981 2874",
            "Jan Kowalski",
            1000.0,
            4.5   
        );
        SavingsService savingsService = new SavingsService(account);

        boolean running = true;

        while (running) {
            System.out.println("\n=== SYMULATOR KONTA OSZCZĘDNOŚCIOWEGO ===");
            System.out.println("1. Wpłać środki");
            System.out.println("2. Wypłać środki");
            System.out.println("3. Symuluj naliczanie odsetek (N miesięcy)");
            System.out.println("4. Pokaż historię operacji");
            System.out.println("5. Pokaż podsumowanie konta");
            System.out.println("6. Wyjdź");
            System.out.print("Wybierz opcję (1-6): ");

            double amount;
            
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    amount = readDouble(scanner, "Podaj kwotę wpłaty: ");
                    if (amount > 0 ) {
                        savingsService.deposit(amount);
                    }else {
                        System.out.println("Kwota musi być dodatnia");
                    }
                    break;
                
                case "2":
                    amount = readDouble(scanner, "Podaj kwotę wypłaty: ");
                    if (amount > 0) {
                        savingsService.withdraw(amount);
                    }else {
                        System.out.println("Kwota musi być dodatnia");
                    }
                    break;

                case "3":
                    int months = readInt(scanner, "Podaj liczbę miesięcy do symulacji: ");
                    if (months > 0) {
                        savingsService.simulateMonths(months);
                    }else {
                        System.out.println("Liczba miesięcy musi być większa od zera");
                    }
                    break;

                case "4":
                    savingsService.printHistory();
                    break;
                
                case "5":
                    savingsService.printSummary();
                    break;

                
                case "6":
                    running = false;
                    System.out.println("Zamykanie symulatora konta. Do zobaczenia!");
                    break;
            
                default:
                    System.out.println("Niepoprawny wybór. Wpisz liczbę od 1 do 6.");
                    break;
            }
        }
        scanner.close();
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("To nie jest poprawna liczba. Spróbuj ponownie.");
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawną liczbę całkowitą.");
            }
        }
    }
    
}