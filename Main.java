import Account.*;
import Bank.*;
import Customer.*;

public class Main {
  public static void main(String[] args) {
    Bank b1 = new Bank("Bank of America");
    Customer c1 = new Customer(1, "John", "Smith", 20, "123 Main St", "123-456-7890", "john@doecom");
    Customer c2 = new Customer(2, "Jane", "Doe", 21, "123 Main St", "123-456-7890", "jane@doecom");

    Account a1 = new Account(1, 100, "USD");
    Account a2 = new Account(2, 300, "MLD");
    c1.addAccount(a1);

    Transaction t1 = new Transaction(1, a1, a2, 100, "USD", "2021-01-01 00:00:00");
    System.out.println(t1);
  }
}
