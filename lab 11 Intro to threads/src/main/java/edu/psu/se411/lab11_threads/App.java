package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static Integer nbAccounts = 10;
	public static Double initialBalance = 1000.0;     // 10 accounts x 1000 = 10000 riyals
	public static Integer nbTransactions = 10000;     // ten thousand threads

	public static void main(String[] args) {
		logger.info("Application is starting...");

		// Run 1: WITHOUT synchronization -> demonstrates the double spending problem
		runSimulation(false);

		// Run 2: WITH synchronization -> the problem is solved
		runSimulation(true);

		logger.info("Application is closing...");
	}

	/** Creates fresh accounts, runs 10000 random transactions in threads, then checks the total. */
	public static void runSimulation(boolean useSynchronization) {
		String mode = useSynchronization ? "WITH synchronization" : "WITHOUT synchronization";
		System.out.println("\n================ " + mode + " ================");
		logger.info("Simulation started " + mode);

		// 1- Create accounts (fresh for every run)
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (Integer i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i.toString(), initialBalance);
		}

		double expectedTotal = getTotalBalance(accounts);
		System.out.printf("Total balance BEFORE transactions: %.2f%n", expectedTotal);

		// 2- Create and start one thread per random transaction
		Thread[] threads = new Thread[nbTransactions];
		for (int i = 0; i < nbTransactions; i++) {
			CryptoAccount randomA = accounts[(int) (Math.random() * nbAccounts)];
			CryptoAccount randomB = accounts[(int) (Math.random() * nbAccounts)];
			Double randomAmount = (double) (Math.random() * 10 + 1);

			Transaction t = new Transaction("tx_" + i, randomA, randomB, randomAmount); // manual id
			t.setUseSynchronization(useSynchronization);

			Thread thread = new Thread(t); // t is the transaction task
			threads[i] = thread;
			thread.start();
		}

		// 3- Wait for all transactions to finish
		for (int i = 0; i < nbTransactions; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				logger.error("Main thread interrupted while waiting: " + e.getMessage());
				Thread.currentThread().interrupt();
			}
		}

		// 4- Print accounts and check the total
		for (CryptoAccount acc : accounts) {
			System.out.println(acc);
		}
		double finalTotal = getTotalBalance(accounts);
		System.out.printf("Total balance AFTER transactions:  %.2f%n", finalTotal);
		logger.info(mode + " -> total after transactions: " + finalTotal);

		if (Math.abs(finalTotal - expectedTotal) > 0.0001) {
			System.out.println("INCONSISTENT! Money was created/lost by the race condition.");
			logger.error(mode + " -> inconsistent total: expected " + expectedTotal + " but got " + finalTotal);
		} else {
			System.out.println("CONSISTENT: total balance is preserved.");
		}
	}

	/** Sum of all balances in the system. */
	public static double getTotalBalance(CryptoAccount[] accounts) {
		double total = 0;
		for (CryptoAccount acc : accounts) {
			total += acc.getBalance();
		}
		return total;
	}
}