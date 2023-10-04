package Customer;

import Account.Account;
import Notification.Notification;
import Offer.Offer;

import java.util.List;

public interface Customer {
  int getCustomerId();
  void addNotification(Notification notification);
  List<Notification> getNotifications();
  void addAccount(Account account);
  List<Account> getAccounts();
  Account getAccountByNumber(String accountNumber);
}
