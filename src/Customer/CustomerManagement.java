package Customer;

import Account.AccountType;
import Bank.Currency;

import java.util.List;

public interface CustomerManagement {
  void addCustomer(Customer customer);
  boolean removeCustomer(int customerIdToRemove);
  Customer getCustomerById(int customerId);
  List<Customer> getAllCustomers();
  void createCustomerAccount(int customerId, AccountType accountType, Currency currency);
}
