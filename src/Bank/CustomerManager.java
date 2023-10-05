package Bank;

import Account.Account;
import Account.AccountType;
import Customer.Customer;
import Customer.CustomerType;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
  private static CustomerManager instance;
  private List<Customer> customers;

  // Private constructor to prevent external instantiation
  private CustomerManager() {
    this.customers = new ArrayList<>();
  }

  public static synchronized CustomerManager getInstance() {
    if (instance == null) {
      instance = new CustomerManager();
    }
    return instance;
  }

  public List<Customer> getAllCustomers() {
    return this.customers;
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  public boolean removeCustomer(int customerIdToRemove) {
    for (Customer customer : customers) {
      if (customer.getCustomerId() == customerIdToRemove) {
        customers.remove(customer);
        return true;
      }
    }
    return false;
  }

  public void createCustomer(CustomerType customerType) {
    Customer customer = customerType.createCustomerFromInput();

    if (customer != null) {
      this.customers.add(customer);
      System.out.println("Customer created successfully.");
      return;
    }

    System.out.println("Customer creation failed.");
  }

  public Customer getCustomerById(int customerId) {
    for (Customer customer : customers) {
      if (customer.getCustomerId() == customerId) {
        return customer;
      }
    }
    return null;
  }

  public void createCustomerAccount(int customerId, AccountType accountType, Currency currency) {
    Customer customerToAddAccount = null;

    for (Customer customer : customers) {
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
  public String toString() {
    return "There are "  + this.customers.size() + " customers" +
        "\n" + this.customers;
  }
}
