package Bank;

import Account.AccountType;
import Customer.Customer;
import Customer.CustomerType;
import Notification.Notification;
import Notification.SecurityNotification;

import java.util.Scanner;

public class CustomerManagerFacade {
  private CustomerManager customerManager;
  private Scanner scanner;

  public CustomerManagerFacade(CustomerManager customerManager) {
    this.customerManager = customerManager;
    this.scanner = new Scanner(System.in);
  }

  public void startMenu() {
    while (true) {
      System.out.println(">>>>> Bank Mode <<<<<");
      System.out.println("1. See ALL customers");
      System.out.println("2. See ONE customer");
      System.out.println("3. Create a customer");
      System.out.println("4. Remove a customer");
      System.out.println("5. Create an account for a customer");
      System.out.println("6. Send security notification to all customers");
      System.out.println("7. Back to main menu");
      System.out.print("Enter your choice: ");
      int customerManagerChoice = scanner.nextInt();

      switch (customerManagerChoice) {
        case 1 -> {
          for (Customer c : this.customerManager.getAllCustomers()) {
            System.out.println(c);
          }
        }
        case 2 -> seeOneCustomer();
        case 3 -> createCustomer();
        case 4 -> removeCustomer();
        case 5 -> createAccount();
        case 6 -> sendNotification();
        case 7 -> {
          System.out.println("Back to main menu...");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void seeOneCustomer() {
    System.out.print("Enter the Customer ID to see: ");
    int customerIdToSee = scanner.nextInt();

    Customer customerToSee = this.customerManager.getCustomerById(customerIdToSee);

    if (customerToSee != null) {
      System.out.println(customerToSee);
    } else {
      System.out.println("Customer not found.");
    }
  }

  private void createCustomer() {
    System.out.print("Enter the customer type (regular/premium/vip): ");
    // Consume next line
    scanner.nextLine();
    String customerTypeInput = scanner.nextLine().toUpperCase();
    CustomerType customerType = CustomerType.valueOf(customerTypeInput);
    this.customerManager.createCustomer(customerType);
  }

  private void removeCustomer() {
    System.out.print("Enter the Customer ID to remove: ");
    int customerIdToRemove = scanner.nextInt();

    if (this.customerManager.removeCustomer(customerIdToRemove)) {
      System.out.println("Customer removed successfully.");
    } else {
      System.out.println("Customer not found.");
    }
  }

  private void createAccount() {
    System.out.print("Enter the Customer ID to add an account: ");
    int customerIdAccount = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    System.out.print("Enter the account type (checking/savings): ");
    AccountType accountType = AccountType.valueOf(scanner.nextLine().toUpperCase());

    System.out.print("Enter the currency (MDL, USD, EUR, or leave empty for default): ");
    String currencyInput = scanner.nextLine().toUpperCase();

    Currency currency = null;

    if (!currencyInput.isEmpty()) {
      try {
        currency = Currency.valueOf(currencyInput);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid currency. Using default national currency.");
      }
    }

    this.customerManager.createCustomerAccount(customerIdAccount, accountType, currency);
  }

  private void sendNotification() {
    System.out.print("Enter the message to send: ");
    scanner.nextLine(); // Consume the newline character
    String message = scanner.nextLine();
    Notification securityNotification = new SecurityNotification(message);
    for (Customer customer : this.customerManager.getAllCustomers()) {
      securityNotification.send(customer);
    }
  }
}
