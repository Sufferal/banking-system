package Customer;

import Account.Account;
import Notification.Notification;
import Offer.OfferObserver;

import java.util.List;

public interface Customer extends OfferObserver {
  int getCustomerId();
  String getPIN();
  void addNotification(Notification notification);
  List<Notification> getNotifications();
  void addAccount(Account account);
  List<Account> getAccounts();
  Account getAccountByNumber(String accountNumber);
}
