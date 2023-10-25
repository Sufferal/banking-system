package Transaction.Payment;

import Transaction.Transaction;
import Account.Account;
import Bank.Currency;

public class ModernPaymentImpl implements ModernPayment {

  @Override
  public void process(Account currentAccount, String toAccountNumber, double amount, Currency currency) {
    Transaction transaction = new Transaction();
    transaction.setFromAccountNumber(currentAccount.getAccountNumber());
    transaction.setToAccountNumber(toAccountNumber);

    if(transaction.getFromAccountNumber() == null || transaction.getToAccountNumber() == null) {
      return;
    }

    transaction.setAmount(amount);
    transaction.setCurrency(currency);
    transaction.commit();
    currentAccount.addTransaction(transaction);
  }
}
