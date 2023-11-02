package Bank;

import Account.AccountType;
import Customer.Customer;
import Customer.CustomerType;
import Customer.Iterator.Iterator;
import Offer.OfferSubject;

import java.util.List;

public interface Manager extends OfferSubject {
  List<Customer> getAllCustomers();
  void addCustomer(Customer customer);
  boolean removeCustomer(int customerIdToRemove);
  void createCustomer(CustomerType customerType);
  Customer getCustomerById(int customerId);
  void createCustomerAccount(int customerId, AccountType accountType, Currency currency);
  Iterator createIterator();
}
