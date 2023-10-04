package Account.Factory;

import Account.Account;
import Account.CheckingAccount;
import Bank.Currency;

public class CheckingAccountFactory implements AccountFactory {
  @Override
  public Account createAccount(double balance, Currency currency) {
    return new CheckingAccount(balance, currency);
  }
}
