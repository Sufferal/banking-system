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
}
