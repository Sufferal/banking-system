package Account.Factory;

import Account.Account;
import Bank.Currency;

public interface AccountFactory {
  Account createAccount(double balance, Currency currency);
}
