import java.util.List;
import java.util.ArrayList;

public class BookService {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✓ Dodano pomyślnie książkę: \"" + book.getTitle() + "\"");
    }

    public void displayAvailableBooks() {
        if(books.isEmpty()) {
            System.out.println("Brak książek");
            return;
        }
        System.out.println("/n ---- LISTA KSIĄŻEK W BIBLIOTECE ----");

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void borrowBook(String id) {
        for (Book book : books) {
            if(book.getId().equals(id)) {
                book.setAvailable(false);
                System.out.println("✓ Wypożyczono książkę: \\\"\" + book.getTitle() + \"\\\"");
            }else {
                System.out.println("✗ Ta książka jest już aktualnie wypożyczona.");
            }
            return;
        }
        System.out.println("✗ Nie znaleziono książki o ID: " + id);
    }

    public void returnBook(String id) {
        for (Book book : books) {
            if(book.getId().equals(id)) {
                if(!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("✓ Zwrócono pomyślnie książkę: \"" + book.getTitle() + "\"");
                }else {
                    System.out.println("✗ Ta książka nie była wypożyczona (już jest dostępna).");
                }
                return;
            }
        }
        System.out.println("✗ Nie znaleziono książki o ID: " + id);
    }
}
