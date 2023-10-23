package Trading;

import java.text.DecimalFormat;

public class ForexFacade {
  private CurrencyConverter converter = new CurrencyConverter();
  private ForexTradingSystem tradingSystem = new ForexTradingSystem();
  private MarketDataFeed marketData = new MarketDataFeed();
  private RiskManagement riskManager = new RiskManagement();
  private ComplianceReporting reporter = new ComplianceReporting();
  private DecimalFormat decimalFormat = new DecimalFormat("#.##");

  public double exchangeCurrency(String fromCurrency, String toCurrency, double amount) {
    double convertedAmount = converter.convert(fromCurrency, toCurrency, amount);
    // Format to two decimal places
    return Double.parseDouble(decimalFormat.format(convertedAmount));
  }

  public String executeForexTrade(String currencyPair, double amount, String orderType) {
    double exchangeRate = marketData.getExchangeRate(currencyPair);
    double stopLossPrice = calculateStopLoss(exchangeRate, amount);

    if (riskManager.setStopLoss(currencyPair, amount, stopLossPrice)) {
      String tradeConfirmation = tradingSystem.executeTrade(currencyPair, amount, orderType);
      reporter.generateReport(tradeConfirmation);
      return tradeConfirmation;
    }

    return "Trade failed due to risk management issues";
  }

  private double calculateStopLoss(double exchangeRate, double amount) {
    // Simulate complex stop-loss calculation based on market conditions
    double marketVolatility = Math.random() * 0.2;
    double stopLossFactor = 1.5;
    return exchangeRate - (marketVolatility * stopLossFactor);
  }
}
