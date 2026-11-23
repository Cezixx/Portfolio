import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService();

        bookService.addBook(new Book("1", "Wiedźmin: Ostatnie Życzenie", "Andrzej Sapkowski"));
        bookService.addBook(new Book("2", "Pan Tadeusz", "Adam Mickiewicz"));

        boolean running = true;

        while (running) {
            System.out.println("\n ==== SYSTEM ZARZADZĄDZANIA BIBLIOTEKĄ ===");
            System.out.println("1. Wyświetl wszystkie książki.");
            System.out.println("2. Dodaj nową książkę.");
            System.out.println("3. Wypożycz książkę.");
            System.out.println("4. Zwróć książkę.");
            System.out.println("5. Wyjdź z programu.");
            System.out.println("Wybierz opcję (1-5)");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    bookService.displayAvailableBooks();
                    break;
                
                case "2":
                    System.out.println("Podaj unikalne ID książki (np. ISBN):");
                    String id = scanner.nextLine();
                    System.out.println("Podaj tytuł książki: ");
                    String title = scanner.nextLine();
                    System.out.println("Podaj autora książki: ");
                    String author = scanner.nextLine();

                    Book newBook = new Book(id, title, author);
                    bookService.addBook(newBook);
                    break;

                case "3":
                    System.out.println("Podaj ID książki, którą chcesz wypożyczyć:");
                    scanner.nextLine();
                    String returnId = scanner.nextLine();
                    bookService.borrowBook(returnId);
                    break;

                case "4":
                    System.out.println("");
                    scanner.nextLine();
                    String returnID = scanner.nextLine();
                    bookService.returnBook(returnID);
                    break;
                default:
                    System.out.println("Nie ma takiej opcji! Wybierz liczbę od 1 do 5.");
                    break;
            }
        }
        scanner.close();
    }
}