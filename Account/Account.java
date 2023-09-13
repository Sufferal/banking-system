package Account;

public class Account {
  private int number;
  private double balance;
  private String currency;

  public Account(int number, double balance, String currency) {
    this.number = number;
    this.balance = balance;
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "(Account) " + number + " has " + balance + " " + currency;
  }
}
