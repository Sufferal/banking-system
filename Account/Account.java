package Account;

import Bank.Currency;

public class Account {
  private int number;
  private double balance;
  private Currency currency;

  public Account(int number, double balance, Currency currency) {
    this.number = number;
    this.balance = balance;
    this.currency = currency;
  }

  public void deposit(double amount) {
    balance += amount;
  }

  public boolean withdraw(double amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    }
    return false;
  }

  public double getBalance() {
    return balance;
  }

  public Currency getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "(Account) " + number + " has " + balance + " " + currency;
  }
}
