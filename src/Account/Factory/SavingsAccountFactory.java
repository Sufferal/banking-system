package Account.Factory;

import Account.Account;
import Account.SavingsAccount;
import Bank.Currency;

public class SavingsAccountFactory implements AccountFactory {
  @Override
  public Account createAccount(double balance, Currency currency) {
    return new SavingsAccount(balance, currency);
  }
}
