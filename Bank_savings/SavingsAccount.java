package Bank_savings;

import java.util.ArrayList;
import java.util.List;

public class SavingsAccount {
    private final String accountNumber;
    private String owner;
    private double balance;
    private double annualInterestRate;
    private final List<Operation> history = new ArrayList<>();

    public SavingsAccount(String accountNumber, String owner, double balance, double annualInterestRate) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getOwner() {
        return this.owner;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getAnnualInterestRate() {
        return this.annualInterestRate;
    }

    public List<Operation> getHistory() {
        return this.history;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void addOperation(Operation operation) {
        if (operation != null) {
            this.history.add(operation);
        }
    }
    

    @Override
    public String toString() {
        return String.format(
            "Konto %s | Właściciel: %s | Saldo: %.2f zł | Oprocentowanie: %.2f%% w skali roku",
            this.accountNumber,
            this.owner,
            this.balance,
            this.annualInterestRate
        ); 
    }
}