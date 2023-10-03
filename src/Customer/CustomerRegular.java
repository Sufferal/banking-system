package Customer;

import Account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerRegular implements Customer {
  private int customerId;
  private String firstName;
  private String lastName;
  private int age;
  private String address;
  private String phone;
  private List<Account> accounts;

  public CustomerRegular(int customerId, String firstName, String lastName, int age, String address, String phone) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
    this.phone = phone;
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
    sb.append("(Customer Regular) #").append(customerId).append(" ").append(firstName).append(" ").append(lastName)
        .append(", ").append(age).append(" y.o. lives at ").append(address)
        .append("\n\t\t\tContact: ").append(phone)
        .append("\n\t\t\tAccounts:\n");

    for (Account account : this.accounts) {
      sb.append("\t\t\t").append(account).append("\n");
    }

    return sb.toString();
  }
}
