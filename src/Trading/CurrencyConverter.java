package Trading;

public class CurrencyConverter {
  public double convert(String fromCurrency, String toCurrency, double amount) {
    double conversionFactor = calculateExchangeRate(amount, fromCurrency, toCurrency);
    return amount * conversionFactor;
  }

  private double calculateExchangeRate(double amount, String fromCurrency, String toCurrency) {
    // Simulate complex exchange rate calculation using the Purchasing Power Parity (PPP) theorem
    double inflationRate = Math.random() * 0.02;
    // PPP theorem calculation
    return Math.exp(inflationRate) * amount;
  }
}
