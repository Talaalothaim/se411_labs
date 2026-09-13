package lab05_exceptions;

public class InsufficientFundsException extends Exception {
 
    private static final long serialVersionUID = 1L;
 
    private final double balance;
    private final double requested;
 
    public InsufficientFundsException(double balance, double requested) {
        super(String.format(
                "Insufficient funds: requested %.2f SAR but the wallet balance is %.2f SAR (short by %.2f SAR).",
                requested, balance, requested - balance));
        this.balance = balance;
        this.requested = requested;
    }
 
    public double getBalance() {
        return balance;
    }
 
    public double getRequested() {
        return requested;
    }
 
    /** How much extra money the user would have needed. */
    public double getShortfall() {
        return requested - balance;
    }
}
 