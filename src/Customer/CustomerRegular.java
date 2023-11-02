package Customer;

import Account.Account;
import Notification.Notification;
import Notification.OfferNotification;
import Offer.Offer;

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
  private String pin;
  private List<Account> accounts;
  private List<Notification> notifications;

  public CustomerRegular(int customerId, String firstName, String lastName, int age, String address, String phone) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
    this.phone = phone;
    this.pin = "1234";
    this.accounts = new ArrayList<Account>();
    this.notifications = new ArrayList<Notification>();
  }

  public CustomerRegular(int customerId, String firstName, String lastName, int age, String address, String phone,
                         List<Account> accounts, List<Notification> notifications) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
    this.phone = phone;
    this.accounts = accounts;
    this.pin = "1234";
    this.notifications = notifications;
  }

  @Override
  public int getCustomerId() { return this.customerId; }

  @Override
  public String getPIN() {
    return this.pin;
  }

  @Override
  public void addNotification(Notification notification) {
    this.notifications.add(notification);
  }

  @Override
  public List<Notification> getNotifications() { return this.notifications; }

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

    sb.append("\t\t\tNotifications:\n");
    for (Notification notification : this.notifications) {
      sb.append("\t\t\t").append(notification).append("\n");
    }

    return sb.toString();
  }

  @Override
  public void update(Offer offer) {
    this.notifications.add(new OfferNotification(offer));
  }

  // Builder
  public static class Builder {
    private int customerId;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String phone;
    private List<Account> accounts = new ArrayList<Account>();
    private List<Notification> notifications = new ArrayList<Notification>();

    public Builder(int customerId) {
      this.customerId = customerId;
    }

    public Builder setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder setLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder setAge(int age) {
      this.age = age;
      return this;
    }

    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    public Builder setPhone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder addAccount(Account account) {
      this.accounts.add(account);
      return this;
    }

    public Builder addNotification(Notification notification) {
      this.notifications.add(notification);
      return this;
    }

    public CustomerRegular build() {
      return new CustomerRegular(customerId, firstName, lastName, age, address, phone, accounts, notifications);
    }
  }
}
