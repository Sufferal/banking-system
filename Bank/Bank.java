package Bank;
import Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Bank {
  private String name;
  private List<Customer> customers;

  public Bank(String name) {
    this.name = name;
    this.customers = new ArrayList<>();
  }

  public void addCustomer(Customer customer) {
    customers.add(customer);
  }

  @Override
  public String toString() {
    return "(Bank) " + name + " has " + customers.size() + " customers";
  }
}
