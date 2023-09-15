package Account;

import Bank.Currency;

public class SavingsAccount extends Account {
  public SavingsAccount(double balance, Currency currency) {
    super(balance, currency);
  }

  @Override
  public void deposit(double amount) {

  }

  @Override
  public void withdraw(double amount) {

  }
}
