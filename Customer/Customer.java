package Customer;

import Account.Account;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private int customerId;
  private String firstName;
  private String lastName;
  private int age;
  private String address;
  private String phone;
  private String email;
  private List<Account> accounts;

  public Customer(int customerId, String firstName, String lastName, int age, String address, String phone, String email) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.accounts = new ArrayList<Account>();
  }

  public void addAccount(Account account) {
    accounts.add(account);
  }

  @Override
  public String toString() {
    return "(Customer) #" + customerId + " " + firstName + " " + lastName + ", " + age + " y.o. lives at " + address +
        "\n\tContacts: " + phone + ", " + email +
        "\n\tAccounts: " + accounts.size();
  }
}
