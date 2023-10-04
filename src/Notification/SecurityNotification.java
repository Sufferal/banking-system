package Notification;

import Customer.Customer;
import Bank.CustomerManager;

import java.util.List;

public class SecurityNotification implements Notification {
  private List<Customer> customers;
  private String message;

  public SecurityNotification(String message) {
    this.message = message;
    this.customers = CustomerManager.getInstance().getAllCustomers();
  }

  @Override
  public void send() {
    for (Customer customer : this.customers) {
      customer.addNotification(this);
    }
  }

  @Override
  public String toString() {
    return "(SECURITY NOTIFICATION): " + this.message.toUpperCase();
  }
}
