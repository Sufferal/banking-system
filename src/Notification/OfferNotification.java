package Notification;

import Bank.CustomerManager;
import Customer.Customer;

import java.util.List;

public class OfferNotification implements Notification {
  private String message;

  public OfferNotification(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "(Offer notification): " + this.message;
  }

  @Override
  public void send() {

  }
}
