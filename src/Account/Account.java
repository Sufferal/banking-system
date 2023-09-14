package Account;

import Bank.Currency;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements TransactionExecutor {
  private int number;
  private double balance;
  private Currency currency;
  private List<Transaction> transactions;

  public Account(int number, double balance, Currency currency) {
    this.number = number;
    this.balance = balance;
    this.currency = currency;
    this.transactions = new ArrayList<>();
  }

  public int getNumber() { return this.number; }
  public double getBalance() { return this.balance; }
  public Currency getCurrency() { return this.currency; }
  public List<Transaction> getTransactions() { return this.transactions; }

  public void addTransaction(Transaction transaction) {
    this.transactions.add(transaction);
  }

  public abstract void deposit(double amount);
  public abstract void withdraw(double amount);

  @Override
  public void executeTransaction(Transaction transaction) {

  }

  @Override
  public String toString() {
    return "(Account) " + number + " has " + balance + " " + currency;
  }
}
