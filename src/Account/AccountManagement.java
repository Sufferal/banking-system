package Account;

import Bank.Currency;

public interface AccountManagement {
  String generateAccountNumber();
  void deposit(double amount, Currency transactionCurrency);
  void withdraw(double amount, Currency transactionCurrency);
}
