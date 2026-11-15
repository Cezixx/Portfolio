import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        System.out.println("==== MENEDŻER WYDATKÓW ====");

        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Wyświetl wszystkie wydatki");
            System.out.println("2. Dodaj nowy wydatek");
            System.out.println("3. Pokaż sumę wydatków");
            System.out.println("4. Wyjście");
            System.out.print("Wybierz opcję (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
            case 1:
                manager.displayExpenses();
                break;

            case 2:
                System.out.println("Podaj kategorię: ");
                String category = scanner.nextLine();

                System.out.println("Podaj kwotę w PLN: ");
                String amountStr = scanner.nextLine().replace(",", ".");
                double amount = Double.parseDouble(amountStr);
                scanner.nextLine();

                System.out.println("Podaj opis: ");
                String description = scanner.nextLine();

                Expense expense = new Expense(category, choice, description);
                manager.addExpense(expense);
                break;

            case 3:
                manager.showTotal();
                break;
            
            case 4:
                manager.displayExpenses();
                    System.out.println("Podaj numer wydatku do usunięcia: ");
                    int indexToRemove = scanner.nextInt();
                    scanner.nextLine();
                    manager.removeExpense(indexToRemove);
                break;

            case 5:
                System.out.println("Do widzenia!");
                running = false;
                break;

            default:
                System.out.println("Niepoprawna opcja! Wybierz liczbę od 1 do 4.");
            }
        }

    }
}
