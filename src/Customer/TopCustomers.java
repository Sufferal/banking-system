package Customer;

import Account.CheckingAccount;
import Account.SavingsAccount;
import Bank.Currency;
import Bank.CustomerManager;
import Notification.SecurityNotification;

import java.util.List;

public class TopCustomers {
  private List<Customer> customers;
  private List<Customer> topCustomers;

  public TopCustomers() {
    this.customers = CustomerManager.getInstance().getAllCustomers();
  }

  public List<Customer> getTopCustomers() {
    return this.topCustomers;
  }

  public void createTopCustomers() {
    CustomerVIP.Builder builder = new CustomerVIP.Builder(3);

    Customer customer_1 = builder.setNickname("Radagon")
        .addAccount(new CheckingAccount(1257, Currency.MDL))
        .addAccount(new SavingsAccount(198, Currency.EUR))
        .addAccount(new CheckingAccount(134, Currency.USD))
        .addAccount(new SavingsAccount(953, Currency.EUR))
        .build();
    this.customers.add(customer_1);

    builder = new CustomerVIP.Builder(4);
    Customer customer_2 = builder.setNickname("Ripper")
        .addAccount(new CheckingAccount(4397, Currency.MDL))
        .addAccount(new SavingsAccount(8932, Currency.EUR))
        .addAccount(new CheckingAccount(13789, Currency.USD))
        .addAccount(new SavingsAccount(9823, Currency.EUR))
        .build();
    this.customers.add(customer_2);

    builder = new CustomerVIP.Builder(5);
    Customer customer_3 = builder.setNickname("Zeus")
        .addAccount(new CheckingAccount(3123, Currency.MDL))
        .addAccount(new SavingsAccount(5235, Currency.EUR))
        .addAccount(new CheckingAccount(234251, Currency.USD))
        .addAccount(new SavingsAccount(31235, Currency.EUR))
        .addNotification(new SecurityNotification("Change your PIN."))
        .build();
    this.customers.add(customer_3);
  }
}
