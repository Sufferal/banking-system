package Account.Decorator;

import Account.Account;

public class AccountDecorator implements AccountAction {
  protected AccountAction wrapper;

  public AccountDecorator(AccountAction account) {
    this.wrapper = account;
  }

  @Override
  public Account execute() {
    return this.wrapper.execute();
  }
}
