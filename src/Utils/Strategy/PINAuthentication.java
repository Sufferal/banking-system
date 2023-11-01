package Utils.Strategy;

import java.util.Scanner;

public class PINAuthentication implements Authentication {
  private String userPIN;

  public PINAuthentication(String pin) {
    this.userPIN = pin;
  }

  @Override
  public boolean authenticate() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your PIN: ");
    String pin = scanner.nextLine();
    return pin.equals(userPIN);
  }
}
