package Account.Decorator;

import Account.Account;

public class OverdraftProtectionDecorator extends AccountDecorator {

  public OverdraftProtectionDecorator(AccountAction account) {
    super(account);
  }

  @Override
  public Account execute() {
    Account account = super.execute();

    if (account.getBalance() < 0 && Math.abs(account.getBalance()) <= 20) {
      double overdraftAmount = Math.abs(account.getBalance());
      account.deposit(overdraftAmount, account.getCurrency());
      System.out.println("An overdraft of " + overdraftAmount + " " + account.getCurrency() + " has been added to your account.");
    }

    return account;
  }
}
