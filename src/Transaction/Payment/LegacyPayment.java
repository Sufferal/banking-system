package Transaction.Payment;

import Account.Account;
import Bank.Currency;

public interface LegacyPayment {
  void initialize(String fromAccountNumber, String toAccountNumber);
  boolean transfer(double totalAmount, Currency currency);
  void end(boolean successful, Account currentAccount);
}
