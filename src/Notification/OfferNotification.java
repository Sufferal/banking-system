package Notification;

import Customer.Customer;
import Offer.Offer;

public class OfferNotification implements Notification {
  private Offer offer;

  public OfferNotification(Offer offer) {
    this.offer = offer;
  }

  @Override
  public String toString() {
    return "(Offer notification): " + this.offer.getOfferDetails();
  }

  @Override
  public void send(Customer customer) {
    customer.addNotification(this);
  }
}
