package Offer;

import Bank.Currency;

public class LoanOffer implements Offer {
  private int amount;
  private Currency currency;

  public LoanOffer(int amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  @Override
  public Offer clone() throws CloneNotSupportedException {
    return (LoanOffer) super.clone();
  }

  public int getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }

  @Override
  public String toString() {
    return "LoanOffer[ " + amount + " " + currency + " ]";
  }
}
