package Trading;

public class MarketDataFeed {
  public double getExchangeRate(String currencyPair) {
    // Simulate complex market data integration
    double exchangeRate = 1.0;
    if (currencyPair.equals("USD/EUR")) {
      exchangeRate = 1.2 + Math.random() * 0.1;
    }
    return exchangeRate;
  }
}
