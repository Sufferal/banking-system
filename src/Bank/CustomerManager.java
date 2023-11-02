package Bank;

import Account.Account;
import Account.AccountType;
import Customer.Customer;
import Customer.CustomerType;
import Customer.Iterator.CustomerIterator;
import Customer.Iterator.Iterator;
import Offer.Offer;
import Offer.OfferObserver;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager implements Manager {
  private static CustomerManager instance;
  private List<Customer> customers;
  private List<OfferObserver> offerObservers;
  private List<Offer> offers;

  // Private constructor to prevent external instantiation
  private CustomerManager() {
    this.customers = new ArrayList<>();
    this.offerObservers = new ArrayList<>();
    this.offers = new ArrayList<>();
  }

  public static synchronized CustomerManager getInstance() {
    if (instance == null) {
      instance = new CustomerManager();
    }
    return instance;
  }

  @Override
  public List<Customer> getAllCustomers() {
    return this.customers;
  }

  @Override
  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  @Override
  public boolean removeCustomer(int customerIdToRemove) {
    Iterator customerIterator = this.createIterator();

    while (customerIterator.hasNext()) {
      Customer customer = (Customer) customerIterator.next();
      if (customer.getCustomerId() == customerIdToRemove) {
        customers.remove(customer);
        return true;
      }
    }

    return false;
  }

  @Override
  public void createCustomer(CustomerType customerType) {
    Customer customer = customerType.createCustomerFromInput();

    if (customer != null) {
      this.customers.add(customer);
      System.out.println("Customer created successfully.");
      return;
    }

    System.out.println("Customer creation failed.");
  }

  @Override
  public Customer getCustomerById(int customerId) {
    Iterator customerIterator = this.createIterator();

    while (customerIterator.hasNext()) {
      Customer customer = (Customer) customerIterator.next();
      if (customer.getCustomerId() == customerId) {
        return customer;
      }
    }
    return null;
  }

  @Override
  public void createCustomerAccount(int customerId, AccountType accountType, Currency currency) {
    Customer customerToAddAccount = null;

    Iterator customerIterator = this.createIterator();

    while (customerIterator.hasNext()) {
      Customer customer = (Customer) customerIterator.next();
      if (customer.getCustomerId() == customerId) {
        customerToAddAccount = customer;
        break;
      }
    }

    if (customerToAddAccount == null) {
      System.out.println("Customer not found.");
      return;
    }

    Account newAccount = null;
    Currency accountCurrency = currency == null ? Currency.MDL : currency;
    newAccount = accountType.createAccount(100, accountCurrency);
    customerToAddAccount.addAccount(newAccount);
  }

  @Override
  public Iterator createIterator() {
    return new CustomerIterator(this.customers);
  }

  @Override
  public String toString() {
    return "There are "  + this.customers.size() + " customers" +
        "\n" + this.customers;
  }

  @Override
  public void registerObserver(OfferObserver observer) {
    this.offerObservers.add(observer);
  }

  @Override
  public void removeObserver(OfferObserver observer) {
    this.offerObservers.remove(observer);
  }

  @Override
  public void notifyObservers(Offer offer) {
    for (OfferObserver observer : this.offerObservers) {
      observer.update(offer);
    }
  }

  public void publishOffer(Offer offer) {
    this.offers.add(offer);
    this.notifyObservers(offer);
  }
}
