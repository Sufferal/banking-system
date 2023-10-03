package Customer;

import Account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerVIP implements Customer {
  private int customerId;
  private List<Account> accounts;

  public CustomerVIP(int customerId) {
    this.customerId = customerId;
    this.accounts = new ArrayList<Account>();
  }

  @Override
  public int getCustomerId() { return this.customerId; }

  @Override
  public List<Account> getAccounts() {
    return this.accounts;
  }

  @Override
  public void addAccount(Account account) {
    this.accounts.add(account);
  }

  @Override
  public Account getAccountByNumber(String accountNumber) {
    for (Account account : this.accounts) {
      if (Objects.equals(account.getAccountNumber(), accountNumber)) {
        return account;
      }
    }

    return null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(Customer VIP) #").append(customerId)
        .append("\n\t\t\tAccounts:\n");

    for (Account account : this.accounts) {
      sb.append("\t\t\t").append(account).append("\n");
    }

    return sb.toString();
  }
}
