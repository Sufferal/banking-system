package Account;

import Bank.Currency;

public class CheckingAccount extends Account {
  private int transactionLimit;
  public CheckingAccount(double balance, Currency currency) {
    super(balance, currency);
    this.transactionLimit = 3;
  }

  public int getTransactionLimit() { return this.transactionLimit; }
  public boolean hasReachedTransactionLimit() {
    return this.getTransactionHistory().size() >= this.transactionLimit;
  }

  @Override
  public String toString() {
    return "CheckingAccount(" +
        "accountNumber='" + this.getAccountNumber() + '\'' +
        ", balance=" + String.format("%.2f", this.getBalance()) +
        ", currency=" + this.getCurrency() +
        ')';
  }
}