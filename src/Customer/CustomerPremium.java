package Customer;

import Account.Account;
import Notification.Notification;
import Offer.Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerPremium implements Customer {
  private int customerId;
  private String firstName;
  private String lastName;
  private String phone;
  private String pin;
  private List<Account> accounts;
  private List<Notification> notifications;
  private List<Offer> offers;

  public CustomerPremium(int customerId, String firstName, String lastName, String phone) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.pin = "5678";
    this.accounts = new ArrayList<Account>();
    this.notifications = new ArrayList<Notification>();
  }

  public CustomerPremium(int customerId, String firstName, String lastName, String phone,
                         List<Account> accounts, List<Notification> notifications) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.pin = "5678";
    this.accounts = accounts;
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
  public List<Notification> getNotifications() {
    return this.notifications;
  }

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
    sb.append("(Customer Premium) #").append(customerId).append(" ").append(firstName).append(" ").append(lastName)
        .append(", ")
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

  // Builder
  public static class Builder {
    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private List<Account> accounts = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();

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

    public CustomerPremium build() {
      return new CustomerPremium(customerId, firstName, lastName, phone, accounts, notifications);
    }
  }
}
