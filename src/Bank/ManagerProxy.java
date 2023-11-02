package Bank;

import Account.AccountType;
import Customer.Customer;
import Customer.CustomerType;
import Customer.Iterator.Iterator;
import Offer.Offer;
import Offer.OfferObserver;

import java.util.List;

public class ManagerProxy implements Manager {
  private Manager realManager;
  // 1 is the highest access level, 3 is the lowest
  private int userAccessLevel;

  public ManagerProxy(Manager realManager, int userAccessLevel) {
    this.realManager = realManager;
    this.userAccessLevel = userAccessLevel;
  }

  @Override
  public List<Customer> getAllCustomers() {
    if (this.userAccessLevel == 1) {
      return this.realManager.getAllCustomers();
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
      return null;
    }
  }

  @Override
  public Iterator createIterator() {
    if (this.userAccessLevel == 1) {
      return this.realManager.createIterator();
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
      return null;
    }
  }

  @Override
  public void registerObserver(OfferObserver observer) {
    if (this.userAccessLevel < 3) {
      this.realManager.registerObserver(observer);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
    }
  }

  @Override
  public void removeObserver(OfferObserver observer) {
    if (this.userAccessLevel < 3) {
      this.realManager.removeObserver(observer);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
    }
  }

  @Override
  public void notifyObservers(Offer offer) {
    if (this.userAccessLevel < 3) {
      this.realManager.notifyObservers(offer);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
    }
  }

  @Override
  public boolean removeCustomer(int customerIdToRemove) {
    if (this.userAccessLevel < 3) {
      return this.realManager.removeCustomer(customerIdToRemove);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
      return false;
    }
  }

  @Override
  public Customer getCustomerById(int customerId) {
    if (this.userAccessLevel < 3) {
      return this.realManager.getCustomerById(customerId);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
      return null;
    }
  }

  @Override
  public void createCustomerAccount(int customerId, AccountType accountType, Currency currency) {
    if (this.userAccessLevel < 4) {
      this.realManager.createCustomerAccount(customerId, accountType, currency);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
    }
  }

  @Override
  public void createCustomer(CustomerType customerType) {
    if (this.userAccessLevel < 4) {
      this.realManager.createCustomer(customerType);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
    }
  }

  @Override
  public void addCustomer(Customer customer) {
    if (this.userAccessLevel < 4) {
      this.realManager.addCustomer(customer);
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
    }
  }
}
