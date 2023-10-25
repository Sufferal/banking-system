package Notification;

import Customer.Customer;
import Bank.CustomerManager;

import java.util.List;

public class SecurityNotification implements Notification {
  private String message;

  public SecurityNotification(String message) {
    this.message = message;
  }

  @Override
  public void send(Customer customer) {
    customer.addNotification(this);
  }

  @Override
  public String toString() {
    return "(SECURITY NOTIFICATION): " + this.message.toUpperCase();
  }
}
