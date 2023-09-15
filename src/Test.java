import Bank.Currency;
import ExchangeRate.ExchangeRateProvider;
import ExchangeRate.LocalExchangeRateProvider;

import java.time.LocalDate;

public class Test {
  public static void main(String[] args) {
    // Create an instance of the LocalExchangeRateProvider
    ExchangeRateProvider exchangeRateProvider = new LocalExchangeRateProvider();

    // Example: Get the exchange rate from USD to EUR for a specific date
    Currency sourceCurrency = Currency.EUR;
    Currency targetCurrency = Currency.USD;
    LocalDate date = LocalDate.of(2023, 9, 15); // Replace with your desired date

    try {
      double exchangeRate = exchangeRateProvider.getExchangeRate(sourceCurrency, targetCurrency, date);
      System.out.println("Exchange rate from " + sourceCurrency + " to " + targetCurrency + " on " + date + " is: " + exchangeRate);
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
    }
  }
}