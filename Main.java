import Account.*;
import Bank.*;
import Customer.*;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("===== Select Mode =====");
      System.out.println("1. Bank");
      System.out.println("2. Customer");
      System.out.println("3. Quit");
      System.out.print("Enter your choice: ");
      int modeChoice = scanner.nextInt();

      if (modeChoice == 1) {
        handleBankMode(scanner);
      } else if (modeChoice == 2) {
        handleCustomerMode(scanner);
      } else if (modeChoice == 3) {
        System.out.println("Exiting the program.");
        break;
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    }

    scanner.close();
  }

  private static void handleBankMode(Scanner scanner) {
    Bank bank = new Bank("MAIB");

    while (true) {
      System.out.println("$$$$$ Bank Mode $$$$$");
      System.out.println("1. See all customers");
      System.out.println("2. Add a customer");
      System.out.println("3. Remove a customer");
      System.out.println("4. Create an account for a customer");
      System.out.println("5. Perform a transaction");
      System.out.println("6. Back to main menu");
      System.out.print("Enter your choice: ");
      int bankChoice = scanner.nextInt();

      if (bankChoice == 1) {
        for(Customer c : bank.getAllCustomers()){
          System.out.println(c);
        }
      } else if (bankChoice == 2) {
        bank.addCustomer(Customer.createCustomerFromInput());
      } else if (bankChoice == 3) {
        // Remove a customer
        System.out.print("Enter the Customer ID to remove: ");
        int customerIdToRemove = scanner.nextInt();

        if (bank.removeCustomer(customerIdToRemove)) {
          System.out.println("Customer removed successfully.");
        } else {
          System.out.println("Customer not found.");
        }
      } else if (bankChoice == 4) {
        System.out.print("Enter the Customer ID to add account: ");
        int customerIdAccount = scanner.nextInt();
        bank.createAccount(customerIdAccount, "Checking");
      } else if (bankChoice == 5) {
        // Perform a transaction
        // Implement logic to perform a transaction
      } else if (bankChoice == 6) {
        // Back to main menu
        break;
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }

  private static void handleCustomerMode(Scanner scanner) {
    // Customer Mode Logic
    while (true) {
      System.out.println("##### Customer Mode ######");
      System.out.println("1. View your accounts");
      System.out.println("2. Request an account");
      System.out.println("3. Transfer money");
      System.out.println("4. Back to main menu");
      System.out.print("Enter your choice: ");
      int customerChoice = scanner.nextInt();

      if (customerChoice == 1) {
        // View your accounts
        // Implement logic to display the customer's accounts
      } else if (customerChoice == 2) {
        // Request an account
        // Implement logic to request a new account
      } else if (customerChoice == 3) {
        // Transfer money
        // Implement logic to transfer money between accounts
      } else if (customerChoice == 4) {
        // Back to main menu
        break;
      } else {
        System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}
