package Account.Decorator;

import Account.Account;
import Notification.AccountNotification;

public class AlertDecorator extends AccountDecorator {
    public AlertDecorator(AccountAction account) {
      super(account);
    }

    @Override
    public Account execute() {
      Account account = super.execute();

      System.out.println("Your account balance is " + account.getBalance() + " " + account.getCurrency() + ".");

      return account;
    }
}
