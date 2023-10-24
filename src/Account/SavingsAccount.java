package Account;

import Account.Decorator.AccountAction;
import Bank.Currency;
import Transaction.Payment.PaymentType;

public class SavingsAccount extends Account implements AccountAction {
  private double interestRate;

  public SavingsAccount(double balance, Currency currency) {
    super(balance, currency, PaymentType.MODERN);
    this.interestRate = 2.0;
  }

  public SavingsAccount(double balance, Currency currency, PaymentType paymentType) {
    super(balance, currency, paymentType);
    this.interestRate = 2.0;
  }

  public double getInterestRate() { return this.interestRate; }
  public void setInterestRate(double interestRate) {
    this.interestRate = interestRate;
  }

  public void addInterest() {
    double interest = this.getBalance() * this.interestRate / 100;
    this.deposit(interest, this.getCurrency());
  }

  @Override
  public String toString() {
    return "SavingsAccount(" +
        "accountNumber='" + this.getAccountNumber() + '\'' +
        ", balance=" + String.format("%.2f", this.getBalance()) +
        ", currency=" + this.getCurrency() +
        ", paymentType=" + this.getPaymentType() +
        ", interestRate=" + this.interestRate +
        ')';
  }

  public Account execute() {
    return this;
  }
}
