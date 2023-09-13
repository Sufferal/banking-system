package Customer;

import Account.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
  private int customerId;
  private String firstName;
  private String lastName;
  private int age;
  private String address;
  private String phone;
  private List<Account> accounts;

  public Customer(int customerId, String firstName, String lastName, int age, String address, String phone) {
    this.customerId = customerId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.address = address;
    this.phone = phone;
    this.accounts = new ArrayList<Account>();
  }

  public int getCustomerId() { return this.customerId; }
  public void addAccount(Account account) {
    this.accounts.add(account);
  }
  public List<Account> getAccounts() {
    return this.accounts;
  }

  // Factory method to create a customer from user input
  public static Customer createCustomerFromInput() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter customer details:");
    System.out.print("Customer ID: ");
    int customerId = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    System.out.print("First Name: ");
    String firstName = scanner.nextLine();

    System.out.print("Last Name: ");
    String lastName = scanner.nextLine();

    System.out.print("Age: ");
    int age = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    System.out.print("Address: ");
    String address = scanner.nextLine();

    System.out.print("Phone: ");
    String phone = scanner.nextLine();

    // Create and return a new Customer object
    return new Customer(customerId, firstName, lastName, age, address, phone);
  }

  @Override
  public String toString() {
    return "(Customer) #" + customerId + " " + firstName + " " + lastName + ", " + age + " y.o. lives at " + address +
        "\n\t\t\tContact: " + phone + ", " +
        "\n\t\t\tAccounts: " + accounts.size();
  }
}
