package Customer;

import Account.Account;
import Notification.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerVIP implements Customer {
  private int customerId;
  private String nickname;
  private String pin;
  private List<Account> accounts = new ArrayList<>();
  private List<Notification> notifications;

  public CustomerVIP(int customerId, String nickname) {
    this.customerId = customerId;
    this.nickname = nickname;
    this.pin = "0000";
    this.accounts = new ArrayList<Account>();
    this.notifications = new ArrayList<Notification>();
  }

  public CustomerVIP(int customerId, String nickname, List<Account> accounts, List<Notification> notifications) {
    this.customerId = customerId;
    this.nickname = nickname;
    this.accounts = accounts;
    this.pin = "0000";
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
    sb.append("(Customer VIP) #").append(customerId).append(" ").append(nickname)
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
    private String nickname;
    private List<Account> accounts = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();

    public Builder(int customerId) {
      this.customerId = customerId;
    }

    public Builder setNickname(String nickname) {
      this.nickname = nickname;
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

    public CustomerVIP build() {
      return new CustomerVIP(customerId, nickname, accounts, notifications);
    }
  }
}
