package ExchangeRate;

import Bank.Currency;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LocalExchangeRateProvider implements ExchangeRateProvider {
  private final Map<String, Double> exchangeRates;

  public LocalExchangeRateProvider() {
    exchangeRates = new HashMap<>();
    exchangeRates.put("USD-MDL", 0.056);
    exchangeRates.put("MDL-USD", 17.92);
    exchangeRates.put("USD-EUR", 1.07);
    exchangeRates.put("EUR-USD", 0.94);
  }

  @Override
  public double getExchangeRate(Currency sourceCurrency, Currency targetCurrency, LocalDate date) {
    String key = sourceCurrency + "-" + targetCurrency;

    if (exchangeRates.containsKey(key)) {
      return exchangeRates.get(key);
    } else {
      throw new IllegalArgumentException("Exchange rate not found for the specified currencies.");
    }
  }
}
