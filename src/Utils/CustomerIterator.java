package Utils;

import Customer.Customer;

import java.util.List;

public class CustomerIterator implements Iterator {
  List<Customer> customers;
  int index = 0;

  public CustomerIterator(List<Customer> customers) {
    this.customers = customers;
  }

  @Override
  public boolean hasNext() {
    return index < customers.size();
  }

  @Override
  public Object next() {
    if (this.hasNext()) {
      return customers.get(index++);
    }
    return null;
  }
}
