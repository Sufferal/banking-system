import Account.*;
import Bank.*;
import Customer.*;
import Notification.*;
import Offer.*;
import Transaction.*;

import java.time.Instant;
import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) throws CloneNotSupportedException {
    CustomerManager bank = CustomerManager.getInstance();

    CustomerRegular.Builder builder = new CustomerRegular.Builder(1);
    Customer customer_1 = builder.setFirstName("Renala")
      .setLastName("Mureșan")
      .setAge(25)
      .setAddress("Str. Mihai Eminescu, 1, Chișinău, Moldova")
      .setPhone("+373 79 000 000")
      .addAccount(new CheckingAccount(12.57, Currency.MDL))
      .addAccount(new SavingsAccount(198, Currency.EUR))
      .build();

    CustomerPremium.Builder builder2 = new CustomerPremium.Builder(2);
    Customer customer_2 = builder2.setFirstName("Vasile")
      .setLastName("Ciobanu")
      .setPhone("+373 79 000 001")
      .addAccount(new CheckingAccount(134, Currency.USD))
      .addAccount(new SavingsAccount(953, Currency.EUR))
      .addNotification(new SecurityNotification("Your account has been hacked :("))
      .build();

    bank.addCustomer(customer_1);
    bank.addCustomer(customer_2);

    TopCustomers topCustomers = new TopCustomers();
    topCustomers.createTopCustomers();

    OfferManager offerManager = new OfferManager();

    offerManager.addOfferPrototype("discount10", new DiscountOffer("10% Discount on Loans"));
    offerManager.addOfferPrototype("bonus1000", new BonusPointsOffer(1000));
    offerManager.addOfferPrototype("loanMIL", new LoanOffer(1000000, Currency.USD));

    for (Customer customer : bank.getAllCustomers()) {
      Offer discountOffer = offerManager.getOfferClone("discount10");
      Offer bonusOffer = offerManager.getOfferClone("bonus1000");
      Offer loanOffer = offerManager.getOfferClone("loanMIL");

      if (customer instanceof CustomerRegular) {
        customer.addNotification(new OfferNotification(discountOffer.toString()));
      } else if (customer instanceof CustomerPremium) {
        customer.addNotification(new OfferNotification(bonusOffer.toString()));
      } else if (customer instanceof CustomerVIP) {
        customer.addNotification(new OfferNotification(loanOffer.toString()));
      }
    }

    while (true) {
      System.out.println("===== Select Mode =====");
      System.out.println("1. Bank");
      System.out.println("2. Customer");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      int modeChoice = scanner.nextInt();

      switch (modeChoice) {
        case 1 -> handleBankMode(bank);
        case 2 -> handleCustomerMode(bank);
        case 3 -> {
          System.out.println("Exiting the program...");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void handleBankMode(CustomerManager bank) {
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
      int bankChoice = Main.scanner.nextInt();

      switch (bankChoice) {
        case 1 -> {
          for (Customer c : bank.getAllCustomers()) {
            System.out.println(c);
          }
        }
        case 2 -> seeOneCustomer(bank);
        case 3 -> createCustomer(bank);
        case 4 -> removeCustomer(bank);
        case 5 -> createAccount(bank);
        case 6 -> sendNotification();
        case 7 -> {
          System.out.println("Back to main menu...");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void handleCustomerMode(CustomerManager bank) {
    System.out.print("Enter your Customer ID: ");
    int customerId = Main.scanner.nextInt();

    Customer customer = bank.getCustomerById(customerId);

    if (customer == null) {
      System.out.println("Customer not found.");
      return;
    }

    while (true) {
      System.out.println("##### Customer Mode ######");
      System.out.println("1. View ALL your accounts");
      System.out.println("2. View ONE account");
      System.out.println("3. Back to main menu ");
      System.out.print("Enter your choice: ");
      int customerChoice = Main.scanner.nextInt();

      switch (customerChoice) {
        case 1 -> {
          for (Account account : customer.getAccounts()) {
            System.out.println(account);
          }
        }
        case 2 -> viewOneAccount(customer);
        case 3 -> {
          System.out.println("Back to main menu...");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void seeOneCustomer(CustomerManager bank) {
    System.out.print("Enter the Customer ID to see: ");
    int customerIdToSee = Main.scanner.nextInt();

    Customer customerToSee = bank.getCustomerById(customerIdToSee);

    if (customerToSee != null) {
      System.out.println(customerToSee);
    } else {
      System.out.println("Customer not found.");
    }
  }

  private static void createCustomer(CustomerManager bank) {
    System.out.print("Enter the customer type (regular/premium/vip): ");
    // Consume next line
    Main.scanner.nextLine();
    String customerTypeInput = Main.scanner.nextLine().toUpperCase();
    CustomerType customerType = CustomerType.valueOf(customerTypeInput);
    bank.createCustomer(customerType);
  }

  private static void removeCustomer(CustomerManager bank) {
    System.out.print("Enter the Customer ID to remove: ");
    int customerIdToRemove = Main.scanner.nextInt();

    if (bank.removeCustomer(customerIdToRemove)) {
      System.out.println("Customer removed successfully.");
    } else {
      System.out.println("Customer not found.");
    }
  }

  private static void createAccount(CustomerManager bank) {
    System.out.print("Enter the Customer ID to add an account: ");
    int customerIdAccount = Main.scanner.nextInt();
    Main.scanner.nextLine(); // Consume the newline character

    System.out.print("Enter the account type (checking/savings): ");
    AccountType accountType = AccountType.valueOf(Main.scanner.nextLine().toUpperCase());

    System.out.print("Enter the currency (MDL, USD, EUR, or leave empty for default): ");
    String currencyInput = Main.scanner.nextLine().toUpperCase();

    Currency currency = null;

    if (!currencyInput.isEmpty()) {
      try {
        currency = Currency.valueOf(currencyInput);
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid currency. Using default national currency.");
      }
    }

    bank.createCustomerAccount(customerIdAccount, accountType, currency);
  }

  private static void sendNotification() {
    System.out.print("Enter the message to send: ");
    Main.scanner.nextLine(); // Consume the newline character
    String message = Main.scanner.nextLine();
    Notification securityNotification = new SecurityNotification(message);
    securityNotification.send();
  }

  private static void viewOneAccount(Customer customer) {
    System.out.print("Enter the account number: ");
    Main.scanner.nextLine(); // Consume the newline character
    String accountNumber = Main.scanner.nextLine();
    Account currentAccount = customer.getAccountByNumber(accountNumber);

    if (currentAccount != null) {
      operationsLoop: while (true) {
        System.out.println("!!!!! Operations !!!!!");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. See current balance");
        System.out.println("4. See transactions ");
        System.out.println("5. Back to customer mode ");
        System.out.print("Enter your choice: ");
        int optionChoice = scanner.nextInt();
        switch (optionChoice) {
          case 1 -> depositFunds(customer, currentAccount);
          case 2 -> withdrawFunds(customer, currentAccount);
          case 3 -> System.out.println(currentAccount);
          case 4 -> {
            for (Transaction transaction : currentAccount.getTransactionHistory()) {
              System.out.println(transaction);
            }
          }
          case 5 -> {
            break operationsLoop;
          }
          default -> {
            System.out.println("Invalid account type. Please try again.");
            return; // Exit the method for invalid account types
          }
        }
      }
    } else {
      System.out.println("Invalid account number.");
    }
  }

  private static void depositFunds(Customer customer, Account currentAccount) {
    // Consume the newline character left in the input stream
    scanner.nextLine();
    System.out.println("Enter the account number to deposit FROM: ");
    String accountNumberFrom = scanner.nextLine();
    Account accountToDepositFrom = customer.getAccountByNumber(accountNumberFrom);

    if (accountToDepositFrom == null) {
      System.out.println("Account not found.");
      return;
    }

    Currency transactionCurrency = accountToDepositFrom.getCurrency();
    System.out.println("Enter the amount to deposit: ");
    double amount = scanner.nextDouble();

    if (!accountToDepositFrom.hasSufficientFunds(amount)) {
      System.out.println("Insufficient funds.");
      return;
    }

    Transaction t = new Transaction(accountToDepositFrom.getAccountNumber(), currentAccount.getAccountNumber(),
        amount, transactionCurrency, Instant.now());

    currentAccount.addTransaction(t);
    accountToDepositFrom.addTransaction(t);

    currentAccount.deposit(amount, transactionCurrency);
    accountToDepositFrom.withdraw(amount, transactionCurrency);
  }

  private static void withdrawFunds(Customer customer, Account currentAccount) {
    // Consume the newline character left in the input stream
    scanner.nextLine();
    System.out.println("Enter the account number to withdraw TO: ");
    String accountNumberTo = scanner.nextLine();
    Account accountToWithdrawTo = customer.getAccountByNumber(accountNumberTo);

    if (accountToWithdrawTo == null) {
      System.out.println("Account not found.");
      return;
    }

    Currency transactionCurrency = currentAccount.getCurrency();
    System.out.println("Enter the amount to withdraw: ");
    double amount = scanner.nextDouble();

    if (!currentAccount.hasSufficientFunds(amount)) {
      System.out.println("Insufficient funds.");
      return;
    }

    Transaction t = new Transaction(currentAccount.getAccountNumber(), accountToWithdrawTo.getAccountNumber(),
        amount, transactionCurrency, Instant.now());

    currentAccount.addTransaction(t);
    accountToWithdrawTo.addTransaction(t);

    accountToWithdrawTo.deposit(amount, transactionCurrency);
    currentAccount.withdraw(amount, transactionCurrency);
  }
}
