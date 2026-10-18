package edu.psu.se411.lab11_threads.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class Transaction implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;
	private boolean useSynchronization = false; // false = part (a-e), true = part (f)

	// Manual id: the caller gives the id, e.g. new Transaction("tx_1", a, b, 5.0)
	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		super();
		if (id == null || sender == null || receiver == null || amount == null || amount < 0)
			throw new IllegalArgumentException();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}

	/** Executes the payment: take money from the sender, give it to the receiver. */
	public void execute() {
		try {
			if (useSynchronization) {
				synchronizedTransfer(sender, receiver, amount);   // part (f): safe
			} else {
				transfer(sender, receiver, amount);               // part (a-e): unsafe
			}
		} catch (InsufficientBalanceException e) {
			logger.error("Transaction " + id + " failed: " + e.getMessage());
		}
	}

	/** WITHOUT synchronization: many threads can run this at the same time -> race condition. */
	private static void transfer(CryptoAccount from, CryptoAccount to, Double amount)
			throws InsufficientBalanceException {
		from.retrieveFromBalance(amount);
		to.addToBalance(amount);
	}

	/**
	 * WITH synchronization (part f): the "synchronized" keyword on this ONE method.
	 * It is static, so all transactions share the same lock (Transaction.class):
	 * only one transfer at a time -> no double spending, no lost updates.
	 */
	private static synchronized void synchronizedTransfer(CryptoAccount from, CryptoAccount to, Double amount)
			throws InsufficientBalanceException {
		from.retrieveFromBalance(amount);
		to.addToBalance(amount);
	}

	/** Called when the thread starts. */
	@Override
	public void run() {
		execute();
	}

	public boolean isUseSynchronization() { return useSynchronization; }
	public void setUseSynchronization(boolean useSynchronization) { this.useSynchronization = useSynchronization; }
	public CryptoAccount getSender() { return sender; }
	public void setSender(CryptoAccount sender) { this.sender = sender; }
	public CryptoAccount getReceiver() { return receiver; }
	public void setReceiver(CryptoAccount receiver) { this.receiver = receiver; }
	public Double getAmount() { return amount; }
	public void setAmount(Double amount) { this.amount = amount; }
	public String getId() { return id; }
}