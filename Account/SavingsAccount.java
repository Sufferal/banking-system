package Account;

import Bank.Currency;

public class SavingsAccount extends Account {
  public SavingsAccount(int accountNumber, double balance, Currency currency) {
    super(accountNumber, balance, currency);
  }

  @Override
  public void withdraw(double amount) {
    // Implement savings account-specific withdrawal rules
    // For example, you may want to check if the balance is above a certain threshold.
    // If not, deny the withdrawal.
    super.withdraw(amount);
  }
}
