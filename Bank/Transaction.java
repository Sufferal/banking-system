package Bank;
import Account.*;

public class Transaction {
  private int transactionId;
  private Account fromAccount;
  private Account toAccount;
  private double amount;
  private String currency;
  private String timestamp;

  public Transaction(int transactionId, Account fromAccountId, Account toAccountId,
                     double amount, String currency, String timestamp) {
    this.transactionId = transactionId;
    this.fromAccount = fromAccountId;
    this.toAccount = toAccountId;
    this.amount = amount;
    this.currency = currency;
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "(Transaction) #" + transactionId + " from Account #" + fromAccount + " to Account #" + toAccount +
        ": " + amount + " " + currency + " at " + timestamp;
  }
}
