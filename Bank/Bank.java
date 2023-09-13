package Bank;

import java.util.ArrayList;
import java.util.List;

import Customer.*;
import Account.*;

public class Bank {
  private String name;
  private List<Customer> customers;

  public Bank(String name) {
    this.name = name;
    this.customers = new ArrayList<>();
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
    return false; // Customer not found
  }

  public List<Customer> getAllCustomers() { return this.customers; }

  public void createAccount(int customerId, AccountType accountType) {
    Customer customerToAddAccount = null;

    for (Customer customer : customers) {
      if (customer.getCustomerId() == customerId) {
        customerToAddAccount = customer;
        break; // Exit the loop once the customer is found
      }
    }

    if (customerToAddAccount == null) {
      System.out.println("Customer not found.");
      return; // Exit the method if the customer is not found
    }

    Account newAccount = null;

    switch (accountType) {
      case CHECKING -> newAccount = new CheckingAccount(1234, 0, Currency.USD);
      case SAVINGS -> newAccount = new SavingsAccount(5678, 0, Currency.USD);
      default -> {
        System.out.println("Invalid account type. Please try again.");
        return; // Exit the method for invalid account types
      }
    }

    customerToAddAccount.addAccount(newAccount);
  }

  public List<Account> getCustomerAccounts(Customer customer) {
    List<Account> customerAccounts = new ArrayList<>();
    for (Customer c : this.customers) {
      if (c.equals(customer)) {
        customerAccounts.addAll(c.getAccounts());
      }
    }
    return customerAccounts;
  }

  public Customer getCustomerById(int customerId) {
    for (Customer customer : this.customers) {
      if (customer.getCustomerId() == customerId) {
        return customer;
      }
    }
    // Customer not found
    return null;
  }

  @Override
  public String toString() {
    return "(Bank) " + this.name + " has " + this.customers.size() + " customers";
  }
}
