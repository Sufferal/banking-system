package Account;

import Account.Factory.AccountFactory;
import Account.Factory.CheckingAccountFactory;
import Account.Factory.SavingsAccountFactory;
import Bank.Currency;

public enum AccountType {
  SAVINGS(new SavingsAccountFactory()),
  CHECKING(new CheckingAccountFactory());

  private final AccountFactory factory;

  AccountType(AccountFactory factory) {
    this.factory = factory;
  }

  public Account createAccount(double balance, Currency currency) {
    return this.factory.createAccount(balance, currency);
  }
}
