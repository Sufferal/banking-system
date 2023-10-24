package Notification;

import Customer.Customer;

public class AccountNotification implements Notification {
  private String message;

  public AccountNotification(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "(Account notification): " + this.message;
  }

  @Override
  public void send(Customer customer) {
    customer.addNotification(this);
  }
}
