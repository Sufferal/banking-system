  package Customer;

  import Account.Account;
  import Bank.Currency;
  import Bank.CustomerManager;
  import Trading.ForexFacade;
  import Transaction.Payment.*;
  import Transaction.Transaction;
  import Utils.Strategy.AuthenticationContext;
  import Utils.Strategy.PINAuthentication;

  import java.time.Instant;
  import java.util.Scanner;

  public class CustomerMenu {
    private CustomerManager customerManager;
    private Scanner scanner;

    public CustomerMenu(CustomerManager customerManager) {
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

      AuthenticationContext authenticationContext = new AuthenticationContext(new PINAuthentication(customer.getPIN()));
      boolean isAuthenticated = authenticationContext.authenticate();

      while (isAuthenticated) {
        System.out.println("##### Customer Mode ######");
        System.out.println("1. View ALL your accounts");
        System.out.println("2. Select an account");
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
          System.out.println("3. Payments");
          System.out.println("4. Trade");
          System.out.println("5. See current balance");
          System.out.println("6. See transactions ");
          System.out.println("7. Back to customer mode ");
          System.out.print("Enter your choice: ");
          int optionChoice = scanner.nextInt();
          switch (optionChoice) {
            case 1 -> depositFunds(customer, currentAccount);
            case 2 -> withdrawFunds(customer, currentAccount);
            case 3 -> handlePayments(customer, currentAccount);
            case 4 -> handleTrade(customer, currentAccount);
            case 5 -> System.out.println(currentAccount);
            case 6 -> {
              for (Transaction transaction : currentAccount.getTransactionHistory()) {
                System.out.println(transaction);
              }
            }
            case 7 -> {
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

    private void handlePayments(Customer customer, Account currentAccount) {
      paymentsLoop: while (true) {
        System.out.println("<<<<< Payments >>>>>");
        System.out.println("1. Electricity Bill (1000 MDL)");
        System.out.println("2. Phone Bill (500 MDL)");
        System.out.println("3. Game (60 USD)");
        System.out.println("4. Back to account operations");
        System.out.print("Enter your choice: ");
        int optionChoice = scanner.nextInt();
        switch (optionChoice) {
          case 1 -> { pay(currentAccount, 1000, Currency.MDL, "Electricity"); }
          case 2 -> { pay(currentAccount, 500, Currency.MDL, "Phone"); }
          case 3 -> { pay(currentAccount, 60, Currency.USD, "Game"); }
          case 4 -> {
            break paymentsLoop;
          }
          default -> {
            System.out.println("Invalid account type. Please try again.");
            return; // Exit the method for invalid account types
          }
        }
      }
    }

    private void pay(Account currentAccount, float amount, Currency currency, String billType) {
      if (currentAccount.getPaymentType() == PaymentType.LEGACY) {
        LegacyPayment legacyPayment = new LegacyPaymentImpl();

        ModernPayment legacyPaymentAdapter = new LegacyPaymentAdapter(legacyPayment);
        legacyPaymentAdapter.process(currentAccount, "123456789", amount, currency);
      } else {
        ModernPayment modernPayment = new ModernPaymentImpl();
        modernPayment.process(currentAccount, "123456789", amount, currency);
      }

      System.out.println(billType + " has been paid/purchased.");
    }

    private void handleTrade(Customer customer, Account currentAccount) {
      ForexFacade facade = new ForexFacade();

      // Currency exchange
      double convertedAmount = facade.exchangeCurrency("USD", "EUR", 1000);
      System.out.println("Converted amount: " + convertedAmount + " EUR");

      // Forex trade
      String tradeConfirmation = facade.executeForexTrade("USD/EUR", 10000, "Market");
      System.out.println("Trade confirmation: " + tradeConfirmation);
    }
  }
