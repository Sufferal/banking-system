package Account;

import Bank.Currency;

public class SavingsAccount extends Account {
  private double interestRate;

  public SavingsAccount(double balance, Currency currency) {
    super(balance, currency);
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
        ", interestRate=" + this.interestRate +
        ')';
  }
}
