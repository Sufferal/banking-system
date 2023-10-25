package Transaction.Payment;

import Account.Account;
import Bank.Currency;

public interface ModernPayment {
  void process(Account currentAccount, String toAccountNumber, double amount, Currency currency);
}
