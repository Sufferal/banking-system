package Customer;

import java.util.Scanner;

public enum CustomerType {
  REGULAR {
    @Override
    public Customer createCustomerFromInput() {
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
      return new CustomerRegular(customerId, firstName, lastName, age, address, phone);
    }
  },
  PREMIUM {
    @Override
    public Customer createCustomerFromInput() {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter customer details:");
      System.out.print("Customer ID: ");
      int customerId = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character

      System.out.print("First Name: ");
      String firstName = scanner.nextLine();

      System.out.print("Last Name: ");
      String lastName = scanner.nextLine();

      System.out.print("Phone: ");
      String phone = scanner.nextLine();

      // Create and return a new Customer object
      return new CustomerPremium(customerId, firstName, lastName, phone);
    }
  },
  VIP {
    @Override
    public Customer createCustomerFromInput() {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter customer details:");
      System.out.print("Customer ID: ");
      int customerId = scanner.nextInt();

      return new CustomerVIP(customerId);
    }
  };

  public abstract Customer createCustomerFromInput();
}
