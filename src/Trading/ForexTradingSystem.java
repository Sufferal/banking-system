package Trading;

import java.util.Random;

public class ForexTradingSystem {
  public String executeTrade(String currencyPair, double amount, String orderType) {
    // Simulate complex forex trading logic
    boolean tradeSuccessful = executeTradingStrategy(currencyPair, amount, orderType);
    if (tradeSuccessful) {
      return "Trade successful";
    } else {
      return "Trade failed";
    }
  }

  private boolean executeTradingStrategy(String currencyPair, double amount, String orderType) {
    // Simulate a trading strategy based on Moving Averages
    double movingAverage = calculateMovingAverage(currencyPair, amount);
    double currentPrice = getRandomMarketPrice();

    if (orderType.equals("Market") && currentPrice > movingAverage) {
      return true;
    }

    return false;
  }

  private double calculateMovingAverage(String currencyPair, double amount) {
    double sumOfLastTrades = Math.random() * amount * 0.1;
    int numTrades = new Random().nextInt(20) + 10;
    return sumOfLastTrades / numTrades;
  }

  private double getRandomMarketPrice() {
    return 1.2 + Math.random() * 0.1;
  }
}
