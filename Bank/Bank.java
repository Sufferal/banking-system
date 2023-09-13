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
    this.customers = new ArrayList<Customer>();
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

  public Account createAccount(int customerId, String accountType) {
    switch (accountType) {
      case "Checking":
        return new CheckingAccount(customerId);
      case "Savings":
        return new SavingsAccount(customerId);
      case "Investment":
        return new InvestmentAccount(customerId);
      default:
        System.out.println("Invalid account type. Please try again.");
    return null;
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

  public void performTransaction(Account fromAccount, Account toAccount, double amount) {
    // Implement transaction logic here
    // You may want to check account balances and perform appropriate actions.
  }

  @Override
  public String toString() {
    return "(Bank) " + this.name + " has " + this.customers.size() + " customers";
  }
}
