package Account;

import Bank.Currency;
import Transaction.Payment.PaymentType;

public class CheckingAccount extends Account {
  private int transactionLimit;

  public CheckingAccount(double balance, Currency currency) {
    super(balance, currency, PaymentType.MODERN);
    this.transactionLimit = 3;
  }

  public CheckingAccount(double balance, Currency currency, PaymentType paymentType) {
    super(balance, currency, paymentType);
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
        ", paymentType=" + this.getPaymentType() +
        ')';
  }
}