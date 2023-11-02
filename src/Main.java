import Account.*;
import Bank.*;
import Customer.*;
import Customer.Iterator.Iterator;
import Notification.*;
import Offer.Offer;
import Transaction.Payment.*;

import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {
    CustomerManager bank = CustomerManager.getInstance();

    CustomerRegular.Builder builder = new CustomerRegular.Builder(1);
    Customer customer_1 = builder.setFirstName("Radagon").setLastName("Beast").setAge(25)
      .setAddress("Str. Mihai Eminescu, 1, Chișinău, Moldova").setPhone("+373 79 000 000")
      .addAccount(new CheckingAccount(12.57, Currency.MDL, PaymentType.LEGACY))
      .addAccount(new SavingsAccount(198, Currency.EUR))
      .build();

    CustomerPremium.Builder builder2 = new CustomerPremium.Builder(2);
    Customer customer_2 = builder2.setFirstName("Vasile").setLastName("Ciobanu").setPhone("+373 79 000 001")
      .addAccount(new CheckingAccount(134, Currency.USD))
      .addAccount(new SavingsAccount(953, Currency.EUR, PaymentType.LEGACY))
      .addNotification(new SecurityNotification("Your account has been hacked :("))
      .build();

    bank.addCustomer(customer_1); bank.registerObserver(customer_1);
    bank.addCustomer(customer_2); bank.registerObserver(customer_2);

    TopCustomers topCustomers = new TopCustomers();
    topCustomers.createTopCustomers();

    while (true) {
      System.out.println("===== Select Mode =====");
      System.out.println("1. Bank");
      System.out.println("2. Customer");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      int modeChoice = scanner.nextInt();
      CustomerManagerMenu customerManagerMenu = new CustomerManagerMenu(bank);
      CustomerMenu customerMenu = new CustomerMenu(bank);

      switch (modeChoice) {
        case 1 -> customerManagerMenu.startMenu();
        case 2 -> customerMenu.startMenu();
        case 3 -> {
          System.out.println("Exiting the program...");
          return;
        }
        default -> System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}
