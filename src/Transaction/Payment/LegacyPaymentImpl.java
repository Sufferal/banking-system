package Transaction.Payment;

import Account.Account;
import Bank.Currency;
import Transaction.Transaction;

public class LegacyPaymentImpl implements LegacyPayment {
  private Transaction transaction;

  public LegacyPaymentImpl() {
    this.transaction = new Transaction();
  }

  @Override
  public void initialize(String fromAccountNumber, String toAccountNumber) {
    this.transaction.setFromAccountNumber(fromAccountNumber);
    this.transaction.setToAccountNumber(toAccountNumber);
  }

  @Override
  public boolean transfer(double totalAmount, Currency currency) {
    if (this.transaction.getFromAccountNumber() == null || this.transaction.getToAccountNumber() == null) {
      return false;
    }

    this.transaction.setAmount(totalAmount);
    this.transaction.setCurrency(currency);

    return true;
  }

  @Override
  public void end(boolean successful, Account currentAccount) {
    if (successful) {
      this.transaction.commit();
      currentAccount.addTransaction(this.transaction);
    } else {
      this.transaction.rollback();
    }
  }
}
