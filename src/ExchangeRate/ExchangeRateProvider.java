package ExchangeRate;

import Bank.Currency;

import java.time.LocalDate;

public interface ExchangeRateProvider {
  double getExchangeRate(Currency sourceCurrency, Currency targetCurrency, LocalDate date);
}
