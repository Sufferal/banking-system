package Account;

import Bank.Currency;

public class CheckingAccount extends Account {
  public CheckingAccount(double balance, Currency currency) {
    super(balance, currency);
  }

  @Override
  public void deposit(double amount) {

  }

  @Override
  public void withdraw(double amount) {

  }
}