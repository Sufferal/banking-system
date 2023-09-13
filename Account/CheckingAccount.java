package Account;

import Bank.Currency;

public class CheckingAccount extends Account {
  public CheckingAccount(int accountNumber, double balance, Currency currency) {
    super(accountNumber, balance, currency);
  }

  @Override
  public void withdraw(double amount) {
    // Implement checking account-specific withdrawal rules
    // For example, you may want to allow overdrafts with a limit.
    super.withdraw(amount);
  }
}