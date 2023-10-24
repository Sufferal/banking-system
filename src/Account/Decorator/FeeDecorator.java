package Account.Decorator;

import Account.Account;

public class FeeDecorator extends AccountDecorator {
    public FeeDecorator(AccountAction account) {
      super(account);
    }

    @Override
    public Account execute() {
      Account account = super.execute();
      account.withdraw(30, account.getCurrency());
      System.out.println("A fee of 30 " + account.getCurrency() + " has been deducted from your account.");
      return account;
    }
}
