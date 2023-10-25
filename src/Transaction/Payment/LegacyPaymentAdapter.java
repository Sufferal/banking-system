package Transaction.Payment;

import Account.Account;
import Bank.Currency;

public class LegacyPaymentAdapter implements ModernPayment {
  private LegacyPayment legacyPayment;

  public LegacyPaymentAdapter(LegacyPayment legacyPayment) {
    this.legacyPayment = legacyPayment;
  }

  @Override
  public void process(Account currentAccount, String toAccountNumber, double amount, Currency currency) {
    this.legacyPayment.initialize(currentAccount.getAccountNumber(), toAccountNumber);
    this.legacyPayment.end(this.legacyPayment.transfer(amount, currency), currentAccount);
  }
}
