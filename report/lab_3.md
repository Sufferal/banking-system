# Topic: Structural Design Patterns

### Course: Software Design Techniques and Mechanisms
### Author: Botnari Ciprian

## Theory
**Structural design patterns** are a category of design patterns that focus on the composition of classes and objects to form larger structures and systems. They provide a way to organize objects and classes in a way that is both flexible and efficient, while allowing for the reuse and modification of existing code. Structural design patterns address common problems encountered in the composition of classes and objects, such as how to create new objects that inherit functionality from existing objects, how to create objects that share functionality without duplicating code, or how to define relationships between objects in a flexible and extensible way.

Some examples of from this category of design patterns are:

* Adapter
* Bridge
* Composite
* Decorator
* Facade
* Flyweight
* Proxy

## Objectives
1. Study and understand the Structural Design Patterns.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. The object creation mechanisms/patterns can now be buried into the functionalities instead of using them into the client.

## Structural design patterns

### Facade
Provides a simplified interface to a library, a framework, or any other complex set of classes.
```
public class ForexFacade {
  private CurrencyConverter converter = new CurrencyConverter();
  private ForexTradingSystem tradingSystem = new ForexTradingSystem();
  private MarketDataFeed marketData = new MarketDataFeed();
  private RiskManagement riskManager = new RiskManagement();
  private ComplianceReporting reporter = new ComplianceReporting();
  private DecimalFormat decimalFormat = new DecimalFormat("#.##");

  public double exchangeCurrency(String fromCurrency, String toCurrency, double amount) {
    double convertedAmount = converter.convert(fromCurrency, toCurrency, amount);
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
```

### Adapter
Allows objects with incompatible interfaces to collaborate.
```
public class LegacyPaymentAdapter implements ModernPayment {
  private LegacyPayment legacyPayment;

  public LegacyPaymentAdapter(LegacyPayment legacyPayment) {
    this.legacyPayment = legacyPayment;
  }

  @Override
  public void process(Account currentAccount, String toAccountNumber, double amount, Currency currency) {
    this.legacyPayment.initialize(currentAccount.getAccountNumber(), toAccountNumber);
    this.legacyPayment.end(this.legacyPayment.transfer(amount, currency), currentAccount);
  }
}
```

### Decorator
Lets you attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors.
```
public class AccountDecorator implements AccountAction {
  protected AccountAction wrapper;

  public AccountDecorator(AccountAction account) {
    this.wrapper = account;
  }

  @Override
  public Account execute() {
    return this.wrapper.execute();
  }
}
```

### Proxy
Lets you provide a substitute or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object.
```
public class ManagerProxy implements Manager {
  private Manager realManager;
  // 1 is the highest access level, 3 is the lowest
  private int userAccessLevel;

  public ManagerProxy(Manager realManager, int userAccessLevel) {
    this.realManager = realManager;
    this.userAccessLevel = userAccessLevel;
  }

  @Override
  public List<Customer> getAllCustomers() {
    if (this.userAccessLevel == 1) {
      return this.realManager.getAllCustomers();
    } else {
      System.out.println("[Access denied] You do not have the privileges to use this method.");
      return null;
    }
  }
  
  ........
}
```

## Results:
### Manager User Access Levels
```
===== Select Mode =====
1. Bank
2. Customer
3. Exit
Enter your choice: 1
>>>>> Bank Mode <<<<<
1. See ALL customers
2. See ONE customer
3. Create a customer
4. Remove a customer
5. Create an account for a customer
6. Send security notification to all customers
7. Test overdraft protection
8. Create managers
9. Back to main menu
Enter your choice: 8
Manager 1 status: 
[Access granted] You have the privileges to use this method.
Manager 2 status: 
[Access denied] You do not have the privileges to use this method.
Manager 3 status: 
[Access denied] You do not have the privileges to use this method.
```

## Conclusion:
The integration of structural design patterns, such as Facade, Adapter, Decorator, and Proxy, has fortified the foundation of my banking system. These patterns have streamlined my codebase, enhanced its flexibility, and simplified interaction with subsystems and external services. While their implementation required initial planning and effort, the long-term benefits in terms of code organization, maintainability, and adaptability have made them a valuable asset in my quest to build a robust and agile banking system.