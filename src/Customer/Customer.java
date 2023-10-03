package Customer;

import Account.Account;

import java.util.List;

public interface Customer {
  int getCustomerId();
  List<Account> getAccounts();
  void addAccount(Account account);
  Account getAccountByNumber(String accountNumber);
}
