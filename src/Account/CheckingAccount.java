package Account;

import Bank.Currency;

public class CheckingAccount extends Account {
  public CheckingAccount(int accountNumber, double balance, Currency currency) {
    super(accountNumber, balance, currency);
  }

  @Override
  public void deposit(double amount) {

  }

  @Override
  public void withdraw(double amount) {

  }
}