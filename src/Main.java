import Account.*;
import Bank.*;
import Customer.*;
import Transaction.*;

import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {
    Bank bank = new Bank("MAIB", Currency.MDL);
    Customer customer_1 = new Customer(1, "John", "Doe", 30, "123 Main St", "123-456-7890");
    Customer customer_2 = new Customer(2, "Ana", "Kramnik", 25, "456 Main St", "123-456-7890");
    Customer customer_3 = new Customer(3, "Vasily", "Rotaru", 67, "789 Main St", "123-456-7890");
    bank.addCustomer(customer_1);
    bank.addCustomer(customer_2);
    bank.addCustomer(customer_3);

    bank.createAccount(customer_1.getCustomerId(), AccountType.CHECKING, Currency.MDL);
    bank.createAccount(customer_1.getCustomerId(), AccountType.SAVINGS, Currency.USD);

    bank.createAccount(customer_2.getCustomerId(), AccountType.CHECKING, Currency.MDL);
    bank.createAccount(customer_2.getCustomerId(), AccountType.SAVINGS, Currency.EUR);

    bank.createAccount(customer_3.getCustomerId(), AccountType.CHECKING, Currency.MDL);
    bank.createAccount(customer_3.getCustomerId(), AccountType.SAVINGS, Currency.USD);

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

  private static void handleBankMode(Bank bank) {
    while (true) {
      System.out.println(">>>>> Bank Mode <<<<<");
      System.out.println("1. See ALL customers");
      System.out.println("2. See ONE customer");
      System.out.println("3. Add a customer");
      System.out.println("4. Remove a customer");
      System.out.println("5. Create an account for a customer");
      System.out.println("6. Back to main menu");
      System.out.print("Enter your choice: ");
      int bankChoice = Main.scanner.nextInt();

      switch (bankChoice) {
        case 1 -> {
          for (Customer c : bank.getAllCustomers()) {
            System.out.println(c);
          }
        }
        case 2 -> seeOneCustomer(bank);
        case 3 -> bank.addCustomer(Customer.createCustomerFromInput());
        case 4 -> removeCustomer(bank);
        case 5 -> createAccount(bank);
        case 6 -> {
          System.out.println("Back to main menu...");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void handleCustomerMode(Bank bank) {
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

  private static void seeOneCustomer(Bank bank) {
    System.out.print("Enter the Customer ID to see: ");
    int customerIdToSee = Main.scanner.nextInt();

    Customer customerToSee = bank.getCustomerById(customerIdToSee);

    if (customerToSee != null) {
      System.out.println(customerToSee);
    } else {
      System.out.println("Customer not found.");
    }
  }

  private static void removeCustomer(Bank bank) {
    System.out.print("Enter the Customer ID to remove: ");
    int customerIdToRemove = Main.scanner.nextInt();

    if (bank.removeCustomer(customerIdToRemove)) {
      System.out.println("Customer removed successfully.");
    } else {
      System.out.println("Customer not found.");
    }
  }

  private static void createAccount(Bank bank) {
    System.out.print("Enter the Customer ID to add account: ");
    int customerIdAccount = Main.scanner.nextInt();

    Main.scanner.nextLine(); // Consume the newline character

    System.out.print("Enter the account type (checking/savings): ");
    AccountType accountType = AccountType.valueOf(Main.scanner.nextLine().toUpperCase());

    System.out.print("Enter the currency (MDL, USD, EUR): ");
    Currency currency = Currency.valueOf(Main.scanner.nextLine().toUpperCase());

    bank.createAccount(customerIdAccount, accountType, currency);
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
          case 1 -> depositAccount(customer, currentAccount);
          case 2 -> {
            // Consume the newline character left in the input stream
            scanner.nextLine();
            System.out.print("Enter the account number to deposit TO: ");
            String accountNumberTo = scanner.nextLine();
            Account accountToDepositTo = null;

            for (Account account : customer.getAccounts()) {
              if (Objects.equals(accountNumberTo, account.getAccountNumber())) {
                accountToDepositTo = account;
                break;
              }
            }

            if (accountToDepositTo == null) {
              System.out.println("Account not found.");
              break operationsLoop; // Exit the method if the account is not found
            }

            currentAccount.addTransaction(new Transaction(2, currentAccount.getAccountNumber(),
                accountToDepositTo.getAccountNumber(), 1, Currency.USD, Instant.now()));
            accountToDepositTo.addTransaction(new Transaction(2, currentAccount.getAccountNumber(),
                accountToDepositTo.getAccountNumber(), 1, Currency.USD, Instant.now()));
            currentAccount.deposit(2, Currency.USD);
          }
          case 3 -> System.out.println(currentAccount);
          case 4 -> {
            for (Transaction transaction : currentAccount.getTransactions()) {
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

  private static void depositAccount(Customer customer, Account currentAccount) {
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

    Transaction t = new Transaction(1, accountToDepositFrom.getAccountNumber(), currentAccount.getAccountNumber(),
        amount, transactionCurrency, Instant.now());

    currentAccount.addTransaction(t);
    accountToDepositFrom.addTransaction(t);
    currentAccount.deposit(amount, transactionCurrency);
  }
}
