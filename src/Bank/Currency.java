package Bank;

public enum Currency {
  MDL("Moldovan Leu"),
  USD("United States Dollar"),
  EUR("Euro"),
  JPY("Japanese Yen"),
  GBP("British Pound Sterling"),
  AUD("Australian Dollar"),
  CAD("Canadian Dollar"),
  CHF("Swiss Franc"),
  CNY("Chinese Yuan"),
  SEK("Swedish Krona"),
  NZD("New Zealand Dollar");

  private final String fullName;

  Currency(String fullName) {
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }
}
