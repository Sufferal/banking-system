package Account;

import Bank.Currency;
import Bank.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Account {
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

  public void deposit(double amount) {
    if (amount > 0) {
      balance += amount;
      System.out.println("Deposit of " + amount + " " + currency + " successful.");
    } else {
      System.out.println("Invalid deposit amount.");
    }
  }

  public void withdraw(double amount) {
    if (amount > 0 && balance >= amount) {
      balance -= amount;
      System.out.println("Withdrawal of " + amount + " " + currency + " successful.");
    } else {
      System.out.println("Invalid withdrawal amount or insufficient funds.");
    }
  }

  @Override
  public String toString() {
    return "(Account) " + number + " has " + balance + " " + currency;
  }
}
