package wallet;

import lab05_exceptions.InsufficientFundsException;
 

public class wallet_account {
 
    private final String ownerName;
    private final String bankAccountNumber;
    private double balance;
 
    public wallet_account(String ownerName, String bankAccountNumber, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        this.ownerName = ownerName;
        this.bankAccountNumber = bankAccountNumber;
        this.balance = openingBalance;
    }
 
    public String getOwnerName() {
        return ownerName;
    }
 
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
 
    public double getBalance() {
        return balance;
    }
 
    /** Adds money to the wallet. */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.printf("Deposited %.2f SAR. New balance: %.2f SAR%n", amount, balance);
    }
 
    /**
     * Moves money from the wallet to the linked bank account.
     * Two different failure modes
     * @return the remaining balance after a successful withdrawal
     * @throws InsufficientFundsException if the amount exceeds the balance
     */
    public double withdrawToBank(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            // Balance is left untouched - the object stays in a valid state.
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
        System.out.printf("Transferred %.2f SAR to bank account %s. Remaining balance: %.2f SAR%n",
                amount, bankAccountNumber, balance);
        return balance;
    }
 
    @Override
    public String toString() {
        return String.format("wallet_account[owner=%s, bank=%s, balance=%.2f SAR]",
                ownerName, bankAccountNumber, balance);
    }
}
 