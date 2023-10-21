package Customer;

import Account.Account;
import Bank.Currency;
import Bank.CustomerManager;
import Transaction.Transaction;

import java.time.Instant;
import java.util.Scanner;

public class CustomerFacade {
  private CustomerManager customerManager;
  private Scanner scanner;

  public CustomerFacade(CustomerManager customerManager) {
    this.customerManager = customerManager;
    this.scanner = new Scanner(System.in);
  }

  public void startMenu() {
    System.out.print("Enter your Customer ID: ");
    int customerId = scanner.nextInt();

    Customer customer = customerManager.getCustomerById(customerId);

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

  private void viewOneAccount(Customer customer) {
    System.out.print("Enter the account number: ");
    scanner.nextLine(); // Consume the newline character
    String accountNumber = scanner.nextLine();
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

  private void depositFunds(Customer customer, Account currentAccount) {
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

  private void withdrawFunds(Customer customer, Account currentAccount) {
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
