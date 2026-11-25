package Bank_savings;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SavingsService {
    private final SavingsAccount account;

    public SavingsService(SavingsAccount account) {
        if(account == null) {
            throw new IllegalArgumentException("Konto oszczędnościowe nie możę być puste."); 
        }
        this.account = account;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Kwota wpłaty musi być większa od zera");
            return;
        }

        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        Operation op = new Operation(
            UUID.randomUUID().toString().substring(0, 0),
            "DEPOSIT",
            amount,
            LocalDateTime.now()
        );
        account.getHistory().add(op);

        System.out.printf("Wpłacono %.2f zł. Nowe saldo: %.2f zł%n", amount, account.getBalance());
    }

    public void withdraw( double amount) {
        if (amount <= 0) {
            System.out.println("Kwota wypłaty musi być większa od zera");
            return;
        }

        if (amount > account.getBalance()) {
            System.out.println("Brak wystarczających środków na koncie");
            return;
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);

        Operation op = new Operation(
            UUID.randomUUID().toString().substring(0, 0),
            "WITHDRAW",
            amount,
            LocalDateTime.now()
        );
        account.getHistory().add(op);

        System.out.printf("Wypłacono %.2f zł. Nowe saldo: %.2f zł%n", amount, account.getBalance());
    }

    public void printHistory() {
        List<Operation> history = account.getHistory();
        if (history == null || history.isEmpty()) {
            System.out.println("Brak operacji na koncie.");
            return; 
        }

        System.out.println("\n--- HISTORIA OPERACJI ---");
        for (Operation op : history) {
            System.out.println(op);
        }
    }

    public void printSummary() {
        System.out.println("\n--- PODSUMOWANIE KONTA ---");
        System.out.println(account);
    }

    public void simulateMonths(int months) {
    if (months <= 0) {
        System.out.println("Liczba miesięcy musi być większa od zera.");
        return;
    }

    double monthlyRate = account.getAnnualInterestRate() / 100.0 / 12.0;

    for (int i = 1; i <= months; i++) {
        double interest = account.getBalance() * monthlyRate;
        account.setBalance(account.getBalance() + interest);

        Operation op = new Operation(
                UUID.randomUUID().toString().substring(0, 8),
                "INTEREST",
                interest,
                LocalDateTime.now()
        );
        account.getHistory().add(op);
    }

    System.out.printf("Zasymulowano %d mies. Nowe saldo: %.2f zł%n", months, account.getBalance());
}
    
}