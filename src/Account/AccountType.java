package Account;

import Bank.Currency;

public enum AccountType {
  SAVINGS {
    @Override
    public Account createAccount(double balance, Currency currency) {
      return new SavingsAccount(balance, currency);
    }
  },
  CHECKING {
    @Override
    public Account createAccount(double balance, Currency currency) {
      return new CheckingAccount(balance, currency);
    }
  };

  public abstract Account createAccount(double balance, Currency currency);
}
