package lab05;
import lab05_exceptions.InsufficientFundsException;
import wallet.wallet_account;

public class ex2 {

	public static void main(String[] args) {
        System.out.println("=== Exercise 2: Online Wallet ===");
 
        wallet_account myWallet = new wallet_account("Tala Alothaim", "SA03-8000-1234", 500.00);
        System.out.println("Opening state: " + myWallet);
        System.out.println();
 
        attemptWithdrawal(myWallet, 200.00);   // succeeds
        attemptWithdrawal(myWallet, 1000.00);  // fails: not enough money
        attemptWithdrawal(myWallet, -50.00);   // fails: invalid amount
        attemptWithdrawal(myWallet, 300.00);   // succeeds, empties the wallet
 
        System.out.println();
        System.out.println("Final state: " + myWallet);
    }
 
    private static void attemptWithdrawal(wallet_account account, double amount) {
        System.out.printf("Requesting withdrawal of %.2f SAR...%n", amount);
        try {
            account.withdrawToBank(amount);
 
        } catch (InsufficientFundsException e) {
            // Specific exception, specific advice for the user.
            System.out.println("  Withdrawal failed -> " + e.getMessage());
            System.out.printf("  Deposit %.2f SAR more, or withdraw at most %.2f SAR.%n",
                    e.getShortfall(), e.getBalance());
 
        } catch (IllegalArgumentException e) {
            System.out.println("  Invalid request -> " + e.getMessage());
        }
        System.out.println();
    }
    
}
