package Account;

import Bank.Currency;
import Transaction.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Account implements TransactionExecutor {
  private String accountNumber;
  private double balance;
  private Currency currency;
  private List<Transaction> transactions;

  public Account(double balance, Currency currency) {
    this.accountNumber = this.generateAccountNumber();
    this.balance = balance;
    this.currency = currency;
    this.transactions = new ArrayList<>();
  }

  public String getNumber() { return this.accountNumber; }
  public double getBalance() { return this.balance; }
  public Currency getCurrency() { return this.currency; }
  public List<Transaction> getTransactions() { return this.transactions; }

  public void addTransaction(Transaction transaction) {
    this.transactions.add(transaction);
  }
  public String generateAccountNumber() {
    StringBuilder accountNumber = new StringBuilder(16);
    Random random = new Random();

    for (int i = 0; i < 16; i++) {
      int digit = random.nextInt(10);
      accountNumber.append(digit);
    }

    return accountNumber.toString();
  }

  public abstract void deposit(double amount);
  public abstract void withdraw(double amount);

  @Override
  public void executeTransaction(Transaction transaction) {

  }

  @Override
  public String toString() {
    return "(Account) " + accountNumber + " has " + balance + " " + currency;
  }
}
