package Bank;

import java.util.ArrayList;
import java.util.List;

import Customer.*;
import Account.*;

public class Bank implements CustomerManagement {
  private String name;
  private Currency nationalCurrency;
  private List<Customer> customers;

  public Bank(String name, Currency nationalCurrency) {
    this.name = name;
    this.nationalCurrency = nationalCurrency;
    this.customers = new ArrayList<>();
  }

  public List<Customer> getAllCustomers() { return this.customers; }

  @Override
  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  @Override
  public boolean removeCustomer(int customerIdToRemove) {
    for (Customer customer : customers) {
      if (customer.getCustomerId() == customerIdToRemove) {
        customers.remove(customer);
        return true;
      }
    }
    return false;
  }

  @Override
  public Customer getCustomerById(int customerId) {
    for (Customer customer : this.customers) {
      if (customer.getCustomerId() == customerId) {
        return customer;
      }
    }
    return null;
  }

  @Override
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

    Currency accountCurrency = currency == null ? this.nationalCurrency : currency;

    switch (accountType) {
      case CHECKING -> newAccount = new CheckingAccount(100, accountCurrency);
      case SAVINGS -> newAccount = new SavingsAccount(100, accountCurrency);
      default -> {
        System.out.println("Invalid account type. Please try again.");
        return;
      }
    }

    customerToAddAccount.addAccount(newAccount);
  }

  @Override
  public String toString() {
    return "(Bank) " + this.name + " has " + this.customers.size() + " customers";
  }
}
