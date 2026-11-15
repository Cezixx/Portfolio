import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseManager {
   private List<Expense> expenses = new ArrayList<>();
   private final String FILE_NAME = "expenses.txt";

   public ExpenseManager(){
    loadFromFile();
   }

   public void addExpense(Expense expense) {
    expenses.add(expense);
    saveToFile();
   }

   public void displayExpenses() {
        if(expenses.isEmpty()) {
            System.out.println("Nie ma zapisanych wydatków.");
            return;
        }

        System.out.println("\\n--- LISTA WYDATKÓW ---");
        for(int i = 0; i< expenses.size(); i++) {
            Expense e = expenses.get(i);
            System.out.printf("%d. [%s] %.2f PLN - %s\n", 
                (i + 1), e.getCategory(), e.getAmount(), e.getDescription());
        }
   }

   public void showTotal() {
    double sum = 0;
    for (Expense e : expenses) {
        sum += e.getAmount();
    }
    System.out.printf("Całkowita suma wydatków: %.2f PLN\n", sum);
   }

   public void removeExpense(int choiceIndex) {
    int index = choiceIndex - 1;

    if(index >= 0 && index < expenses.size()) {
        expenses.remove(index);
        saveToFile();
        System.out.println("Wydatek został usunięty");
    
    }else {
        System.out.println("Błąd: Nie ma wydatku o takim numerze");
    }
   }

   public void loadFromFile() {
    File file = new File(FILE_NAME);
    if(!file.exists()) {
        return;
    }

    expenses.clear();
    try (Scanner fileScanner = new Scanner(file)) {
        while(fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String parts[] = line.split(";");
            
            if(parts.length == 3) {
                String category = parts[0];
                String amountStr = parts[1].replace(",", ".");
                double amount = Double.parseDouble(amountStr);
                String description = parts[2];
                expenses.add(new Expense(category, amount, description));
            }
        }
        
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Błąd podczas wczytywania pliku: " + e.getMessage());
    }
   }

   public void saveToFile() {
    try(PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
        for (Expense e : expenses) {
            writer.println(e.toFileString());
        }
    } catch (IOException e) {
        // TODO: handle exception
        System.out.println("Błąd podczas zapisu do pliku: " + e.getMessage());
    }
   }

}
