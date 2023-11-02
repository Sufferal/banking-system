package Bank;

import Account.AccountType;
import Account.Decorator.AccountAction;
import Account.Decorator.AlertDecorator;
import Account.Decorator.FeeDecorator;
import Account.Decorator.OverdraftProtectionDecorator;
import Account.SavingsAccount;
import Customer.Customer;
import Customer.CustomerType;
import Notification.Notification;
import Notification.SecurityNotification;
import Offer.Offer;
import Customer.Iterator.Iterator;
import Customer.Strategy.AuthenticationContext;
import Customer.Strategy.FaceAuthentication;
import Customer.Strategy.FingerprintAuthentication;

import java.util.Scanner;

public class CustomerManagerMenu {
  private CustomerManager customerManager;
  private Scanner scanner;

  public CustomerManagerMenu(CustomerManager customerManager) {
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
      System.out.println("7. Publish an offer");
      System.out.println("8. Create managers");
      System.out.println("9. Test authentications");
      System.out.println("10. Back to main menu");
      System.out.print("Enter your choice: ");
      int customerManagerChoice = scanner.nextInt();

      switch (customerManagerChoice) {
        case 1 -> printAllCustomers();
        case 2 -> seeOneCustomer();
        case 3 -> createCustomer();
        case 4 -> removeCustomer();
        case 5 -> createAccount();
        case 6 -> sendNotification();
        case 7 -> publishOffer();
        case 8 -> createManagers();
        case 9 -> testAuthentications();
        case 10 -> {
          System.out.println("Back to main menu...");
          return;
        }
        case 11 -> testOffers();
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private void printAllCustomers() {
    Iterator customerIterator = this.customerManager.createIterator();
    while (customerIterator.hasNext()) {
      Customer customer = (Customer) customerIterator.next();
      System.out.println(customer);
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
    Iterator customerIterator = this.customerManager.createIterator();

    while (customerIterator.hasNext()) {
      Customer customer = (Customer) customerIterator.next();
      securityNotification.send(customer);
    }
  }

  private void publishOffer() {
    System.out.print("Enter the message to publish: ");
    scanner.nextLine(); // Consume the newline character
    String message = scanner.nextLine();
    Offer offer = new Offer(message);
    this.customerManager.publishOffer(offer);
  }

  private void testOffers() {
    Customer customer_1 = this.customerManager.getCustomerById(1);
    Customer customer_2 = this.customerManager.getCustomerById(2);

    Offer interestRateOffer = new Offer("We offer you a 0.5% interest rate for your savings account!");
    Offer creditOffer = new Offer("We offer you a 5% interest rate for your credit!");
    this.customerManager.publishOffer(interestRateOffer);

    System.out.println(customer_1); System.out.println(customer_2);

    this.customerManager.removeObserver(customer_1);
    this.customerManager.publishOffer(creditOffer);

    System.out.println(customer_1); System.out.println(customer_2);
  }

  private void testOverdraftProtection() {
    AccountAction savingsAccount = new SavingsAccount(10, Currency.EUR);
    AccountAction account = new AlertDecorator(new OverdraftProtectionDecorator(new FeeDecorator(savingsAccount)));
    System.out.println(savingsAccount);
    account.execute();
    System.out.println(savingsAccount);
  }

  private void testAuthentications() {
    AuthenticationContext authenticationContext = new AuthenticationContext(
      new FaceAuthentication("src/Customer/Strategy/img/face.jpg"));
    System.out.println(authenticationContext.authenticate());
    authenticationContext.setAuthentication(new FingerprintAuthentication(new int[][]{
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    }));
    System.out.println(authenticationContext.authenticate());
  }

  private void createManagers() {
    Manager manager1 = new ManagerProxy(this.customerManager, 1);
    Manager manager2 = new ManagerProxy(this.customerManager, 2);
    Manager manager3 = new ManagerProxy(this.customerManager, 3);

    System.out.println("Manager 1 status: ");
    manager1.getAllCustomers();
    System.out.println("Manager 2 status: ");
    manager2.getAllCustomers();
    System.out.println("Manager 3 status: ");
    manager3.getAllCustomers();
  }
}
