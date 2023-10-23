package Transaction;
import Account.*;
import Bank.Currency;

import java.time.Instant;

public class Transaction {
  private static int lastTransactionId = 0;
  private int transactionId;
  private String fromAccountNumber;
  private String toAccountNumber;
  private double amount;
  private Currency currency;
  private Instant timestamp;

  public Transaction(String fromAccountNumber, String toAccountNumber,
                     double amount, Currency currency, Instant timestamp) {
    this.transactionId = getNextTransactionId();
    this.fromAccountNumber = fromAccountNumber;
    this.toAccountNumber = toAccountNumber;
    this.amount = amount;
    this.currency = currency;
    this.timestamp = timestamp;
  }

  public Transaction() {
    this.transactionId = getNextTransactionId();
    this.fromAccountNumber = "";
    this.toAccountNumber = "";
    this.amount = 0;
    this.currency = Currency.USD;
    this.timestamp = Instant.now();
  }

  private synchronized static int getNextTransactionId() {
    return ++lastTransactionId;
  }

  public Currency getCurrency() {
    return this.currency;
  }
  public Instant getTimestamp() {
    return this.timestamp;
  }
  public String getFromAccountNumber() {
    return this.fromAccountNumber;
  }
  public String getToAccountNumber() {
    return this.toAccountNumber;
  }
  public double getAmount() {
    return this.amount;
  }

  @Override
  public String toString() {
    return "(Transaction) #" + transactionId + " from Account " + fromAccountNumber + " to Account " + toAccountNumber +
        ": " + amount + " " + currency + " at " + timestamp;
  }

  public void commit() {
    System.out.println("Transaction #" + transactionId + " committed.");
  }

  public void rollback() {
    System.out.println("Transaction #" + transactionId + " rolled back.");
  }

  public void setFromAccountNumber(String fromAccountNumber) {
    this.fromAccountNumber = fromAccountNumber;
  }

  public void setToAccountNumber(String toAccountNumber) {
    this.toAccountNumber = toAccountNumber;
  }

  public void setAmount(double totalAmount) {
    this.amount = totalAmount;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }
}
