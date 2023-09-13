import Account.*;
import Bank.*;
import Customer.*;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Bank bank = new Bank("MAIB");
    Customer customer_1 = new Customer(1, "John", "Doe", 30, "123 Main St", "123-456-7890");
    bank.addCustomer(customer_1);
    bank.createAccount(customer_1.getCustomerId(), AccountType.CHECKING);
    bank.createAccount(customer_1.getCustomerId(), AccountType.SAVINGS);
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.println("===== Select Mode =====");
        System.out.println("1. Bank");
        System.out.println("2. Customer");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int modeChoice = scanner.nextInt();

        if (modeChoice == 1) {
          handleBankMode(scanner, bank);
        } else if (modeChoice == 2) {
          handleCustomerMode(scanner, bank);
        } else if (modeChoice == 3) {
          System.out.println("Exiting the program.");
          break;
        } else {
          System.out.println("Invalid choice. Please try again.");
        }
      }
    }
  }

  private static void handleBankMode(Scanner scanner, Bank bank) {
    while (true) {
      System.out.println("$$$$$ Bank Mode $$$$$");
      System.out.println("1. See all customers");
      System.out.println("2. Add a customer");
      System.out.println("3. Remove a customer");
      System.out.println("4. Create an account for a customer");
      System.out.println("5. Back to main menu");
      System.out.print("Enter your choice: ");
      int bankChoice = scanner.nextInt();

      if (bankChoice == 1) {
        for (Customer c : bank.getAllCustomers()) {
          System.out.println(c);
        }
      } else if (bankChoice == 2) {
        bank.addCustomer(Customer.createCustomerFromInput());
      } else if (bankChoice == 3) {
        removeCustomer(scanner, bank);
      } else if (bankChoice == 4) {
        createAccount(scanner, bank);
      } else if (bankChoice == 5) {
        break;
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void handleCustomerMode(Scanner scanner, Bank bank) {
    // Prompt for the customer ID
    System.out.print("Enter your Customer ID: ");
    int customerId = scanner.nextInt();

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
      int customerChoice = scanner.nextInt();

      if (customerChoice == 1) {
        for (Account account : customer.getAccounts()) {
          System.out.println(account);
        }
      } else if (customerChoice == 2) {
        viewOneAccount(scanner, customer);
      } else if (customerChoice == 3) {
        break;
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void removeCustomer(Scanner scanner, Bank bank) {
    System.out.print("Enter the Customer ID to remove: ");
    int customerIdToRemove = scanner.nextInt();

    if (bank.removeCustomer(customerIdToRemove)) {
      System.out.println("Customer removed successfully.");
    } else {
      System.out.println("Customer not found.");
    }
  }

  private static void createAccount(Scanner scanner, Bank bank) {
    System.out.print("Enter the Customer ID to add account: ");
    int customerIdAccount = scanner.nextInt();

    scanner.nextLine(); // Consume the newline character

    System.out.print("Enter the account type (checking/savings): ");
    String accountType = scanner.nextLine().toLowerCase();

    bank.createAccount(customerIdAccount, AccountType.valueOf(accountType.toUpperCase()));
  }

  private static void viewOneAccount(Scanner scanner, Customer customer) {
    System.out.print("Enter the account number: ");
    int accountNumber = scanner.nextInt();
    Account currentAccount = customer.getAccountByNumber(accountNumber);

    if (currentAccount != null) {
      currentAccount.performOperations(scanner);
    } else {
      System.out.println("Invalid account number.");
    }
  }
}

//import Account.*;
//import Bank.*;
//import Customer.*;
//
//import java.time.Instant;
//import java.util.Date;
//import java.util.Scanner;
//
//public class Main {
//  public static void main(String[] args) {
//    Bank bank = new Bank("MAIB");
//    Customer customer_1 = new Customer(1, "John", "Doe", 30, "123 Main St", "123-456-7890");
//    bank.addCustomer(customer_1);
//    bank.createAccount(customer_1.getCustomerId(), "checking");
//    bank.createAccount(customer_1.getCustomerId(), "savings");
//    Scanner scanner = new Scanner(System.in);
//
//    while (true) {
//      System.out.println("===== Select Mode =====");
//      System.out.println("1. Bank");
//      System.out.println("2. Customer");
//      System.out.println("3. Exit");
//      System.out.print("Enter your choice: ");
//      int modeChoice = scanner.nextInt();
//
//      if (modeChoice == 1) {
//        handleBankMode(scanner, bank);
//      } else if (modeChoice == 2) {
//        handleCustomerMode(scanner, bank);
//      } else if (modeChoice == 3) {
//        System.out.println("Exiting the program.");
//        break;
//      } else {
//        System.out.println("Invalid choice. Please try again.");
//      }
//    }
//
//    scanner.close();
//  }
//
//  private static void handleBankMode(Scanner scanner, Bank bank) {
//    while (true) {
//      System.out.println("$$$$$ Bank Mode $$$$$");
//      System.out.println("1. See all customers");
//      System.out.println("2. Add a customer");
//      System.out.println("3. Remove a customer");
//      System.out.println("4. Create an account for a customer");
//      System.out.println("5. Back to main menu");
//      System.out.print("Enter your choice: ");
//      int bankChoice = scanner.nextInt();
//
//      if (bankChoice == 1) {
//        for(Customer c : bank.getAllCustomers()){
//          System.out.println(c);
//        }
//      } else if (bankChoice == 2) {
//        bank.addCustomer(Customer.createCustomerFromInput());
//      } else if (bankChoice == 3) {
//        // Remove a customer
//        System.out.print("Enter the Customer ID to remove: ");
//        int customerIdToRemove = scanner.nextInt();
//
//        if (bank.removeCustomer(customerIdToRemove)) {
//          System.out.println("Customer removed successfully.");
//        } else {
//          System.out.println("Customer not found.");
//        }
//      } else if (bankChoice == 4) {
//        System.out.print("Enter the Customer ID to add account: ");
//        int customerIdAccount = scanner.nextInt();
//
//        // Consume the newline character left in the input stream
//        scanner.nextLine();
//
//        System.out.print("Enter the account type: ");
//        String accountType = scanner.nextLine();
//        bank.createAccount(customerIdAccount, accountType);
//      } else if (bankChoice == 5) {
//        break;
//      } else {
//        System.out.println("Invalid choice. Please try again.");
//      }
//    }
//  }
//
//  private static void handleCustomerMode(Scanner scanner, Bank bank) {
//    // Prompt for the customer ID
//    System.out.print("Enter your Customer ID: ");
//    int customerId = scanner.nextInt();
//
//    // Find the customer by ID in the bank
//    Customer customer = bank.getCustomerById(customerId);
//
//    if (customer == null) {
//      System.out.println("Customer not found.");
//      return; // Exit the method if the customer is not found
//    }
//
//    // Customer Mode Logic
//    while (true) {
//      System.out.println("##### Customer Mode ######");
//      System.out.println("1. View ALL your accounts");
//      System.out.println("2. View ONE account");
//      System.out.println("3. Back to main menu ");
//      System.out.print("Enter your choice: ");
//      int customerChoice = scanner.nextInt();
//
//      if (customerChoice == 1) {
//        for (Account account : customer.getAccounts()) {
//          System.out.println(account);
//        }
//      } else if (customerChoice == 2) {
//        System.out.print("Enter the account number: ");
//        int accountNumber = scanner.nextInt();
//        Account currentAccount = null;
//
//        for (Account account : customer.getAccounts()) {
//          if (accountNumber == account.getNumber()) {
//            currentAccount = account;
//            System.out.println(account);
//            break;
//          }
//        }
//
//        if(currentAccount != null) {
//          operationsLoop: while (true) {
//            System.out.println("!!!!! Operations !!!!!");
//            System.out.println("1. Deposit");
//            System.out.println("2. Withdraw");
//            System.out.println("3. See current balance");
//            System.out.println("4. See transactions ");
//            System.out.println("5. Back to customer mode ");
//            System.out.print("Enter your choice: ");
//            int optionChoice = scanner.nextInt();
//            switch (optionChoice) {
//              case 1 -> {
//                // Consume the newline character left in the input stream
//                scanner.nextLine();
//                System.out.print("Enter the account number to deposit FROM: ");
//                int accountNumberFrom = scanner.nextInt();
//                Account accountToDepositFrom = null;
//
//                for (Account account : customer.getAccounts()) {
//                  if (accountNumberFrom == account.getNumber()) {
//                    accountToDepositFrom = account;
//                    break;
//                  }
//                }
//
//                if (accountToDepositFrom == null) {
//                  System.out.println("Account not found.");
//                  break operationsLoop; // Exit the method if the account is not found
//                }
//
//                currentAccount.addTransaction(new Transaction(1, accountToDepositFrom.getNumber(),
//                                              currentAccount.getNumber(), 2, Currency.USD, Instant.now()));
//                accountToDepositFrom.addTransaction(new Transaction(1, accountToDepositFrom.getNumber(),
//                                              currentAccount.getNumber(), 2, Currency.USD, Instant.now()));
//                currentAccount.deposit(2);
//              }
//              case 2 -> {
//                // Consume the newline character left in the input stream
//                scanner.nextLine();
//                System.out.print("Enter the account number to deposit TO: ");
//                int accountNumberTo = scanner.nextInt();
//                Account accountToDepositTo = null;
//
//                for (Account account : customer.getAccounts()) {
//                  if (accountNumberTo == account.getNumber()) {
//                    accountToDepositTo = account;
//                    break;
//                  }
//                }
//
//                if (accountToDepositTo == null) {
//                  System.out.println("Account not found.");
//                  break operationsLoop; // Exit the method if the account is not found
//                }
//
//                currentAccount.addTransaction(new Transaction(2, currentAccount.getNumber(),
//                    accountToDepositTo.getNumber(), 1, Currency.USD, Instant.now()));
//                accountToDepositTo.addTransaction(new Transaction(2, currentAccount.getNumber(),
//                    accountToDepositTo.getNumber(), 1, Currency.USD, Instant.now()));
//                currentAccount.deposit(2);
//              }
//              case 3 -> System.out.println(currentAccount);
//              case 4 -> {
//                for (Transaction transaction : currentAccount.getTransactions()) {
//                  System.out.println(transaction);
//                }
//              }
//              case 5 -> {
//                break operationsLoop;
//              }
//              default -> {
//                System.out.println("Invalid account type. Please try again.");
//                return; // Exit the method for invalid account types
//              }
//            }
//          }
//        } else {
//          System.out.println("Invalid account number.");
//        }
//      } else if (customerChoice == 3) {
//        break;
//      }  else {
//        System.out.println("Invalid choice. Please try again.");
//      }
//    }
//  }
//}
