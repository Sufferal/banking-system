# Topic: SOLID Principles

### Course: Software Design Techniques and Mechanisms
### Author: Botnari Ciprian

## Theory
**SOLID** is a set of five object-oriented design principles intended to make software designs more maintainable, flexible, and easy to understand. The acronym stands for Single Responsibility Principle, Open-Closed Principle, Liskov Substitution Principle, Interface Segregation Principle, and Dependency Inversion Principle. Each principle addresses a specific aspect of software design, such as the organization of responsibilities, the handling of dependencies, and the design of interfaces. By following these principles, software developers can create more modular, testable, and reusable code that is easier to modify and extend over time. These principles are widely accepted and adopted in the software development community and can be applied to any object-oriented programming language.
## Objectives:
1. Study and understand the SOLID Principles.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. Create a sample project that respects SOLID Principles.

## Implementation:

### Account
The interface that defines the methods for account management.

```
public interface AccountManagement {
  String generateAccountNumber();
  void deposit(double amount, Currency transactionCurrency);
  void withdraw(double amount, Currency transactionCurrency);
}
```

An example for the implementation of the interface.
```
public abstract class Account implements AccountManagement  {
  private String accountNumber;
  private double balance;
  private Currency currency;
  private List<Transaction> transactionHistory;

  public Account(double balance, Currency currency) {
    this.accountNumber = this.generateAccountNumber();
    this.balance = balance;
    this.currency = currency;
    this.transactionHistory = new ArrayList<>();
  }

  @Override
  public String generateAccountNumber() {
    StringBuilder accountNumber = new StringBuilder(16);
    Random random = new Random();

    for (int i = 0; i < 16; i++) {
      int digit = random.nextInt(10);
      accountNumber.append(digit);
    }

    return accountNumber.toString();
  }

  .....
```

### Exchange Rate
The interface that defines the methods for exchange rate.

```
public interface ExchangeRateProvider {
  double getExchangeRate(Currency sourceCurrency, Currency targetCurrency, LocalDate date);
}
```

An example for the implementation of the interface.
```
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

  .....
```

### Bank
The interface that defines the methods for customer management.

```
public interface CustomerManagement {
  void addCustomer(Customer customer);
  boolean removeCustomer(int customerIdToRemove);
  Customer getCustomerById(int customerId);
  List<Customer> getAllCustomers();
  void createCustomerAccount(int customerId, AccountType accountType, Currency currency);
}
```

An example for the implementation of the interface.
```
public class Bank implements CustomerManagement {
  private String name;
  private Currency nationalCurrency;
  private List<Customer> customers;

  public Bank(String name, Currency nationalCurrency) {
    this.name = name;
    this.nationalCurrency = nationalCurrency;
    this.customers = new ArrayList<>();
  }

  public List<Customer> getAllCustomers() { return this.customers; }

  @Override
  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  @Override
  public boolean removeCustomer(int customerIdToRemove) {
    for (Customer customer : customers) {
      if (customer.getCustomerId() == customerIdToRemove) {
        customers.remove(customer);
        return true;
      }
    }
    return false;
  }

  .....
```

## SOLID principles:

### Single Responsibility Principle:
* Account class has a single responsibility, which 
is to handle deposit and withdrawal transactions.
* The LocalExchangeRateProvider class has a single responsibility for providing the local exchange rate.
* The Bank class focuses on managing customers and their accounts.

### Open/Closed Principle:
The application's classes are designed to be open 
for extension but closed for modification. New features or functionality can be added through new 
classes or interfaces without modifying existing code.
* You can introduce more account types or other methods without changing the existing classes.

### Liskov Substitution Principle:
* The application's classes, encompassing interfaces, their implementations, and abstract classes, consistently follow this principle. 
* Subtypes, whether they are implementations of interfaces or extensions of abstract classes, can easily substitute their parent types without causing any problems that might compromise the program's correctness.
* This commitment to the principles guarantees that various classes, regardless of whether they are derived from interfaces or abstract classes, can be used interchangeably without compromising the program's integrity.

### Interface Segregation Principle:
* The interfaces defined in the 
application are focused on specific features.
* AccountManagement and CustomerManagement are examples of such interfaces. 

### Dependency Inversion Principle:
* The application follows Dependency Inversion Principle by relying on 
abstractions, i.e., interfaces rather than concrete implementations.

* Account depends on the AccountManagement interface, not a specific implementation. This allows for the flexibility to use 
different implementations of the accounts without modifying the dependent classes.

## Results:
### View all customers:
```
===== Select Mode =====
1. Bank
2. Customer
3. Exit
Enter your choice: 1
>>>>> Bank Mode <<<<<
1. See ALL customers
2. See ONE customer
3. Add a customer
4. Remove a customer
5. Create an account for a customer
6. Back to main menu
Enter your choice: 1
(Customer) #1 John Doe, 30 y.o. lives at 123 Main St
			Contact: 123-456-7890
			Accounts:
			CheckingAccount(accountNumber='6465744541203746', balance=100.00, currency=MDL)
			SavingsAccount(accountNumber='3639279523107209', balance=100.00, currency=USD, interestRate=2.0)

(Customer) #2 Ana Kramnik, 25 y.o. lives at 456 Main St
			Contact: 123-456-7890
			Accounts:
			CheckingAccount(accountNumber='2099558353074195', balance=100.00, currency=MDL)
			SavingsAccount(accountNumber='5354054593725207', balance=100.00, currency=EUR, interestRate=2.0)

(Customer) #3 Vasily Rotaru, 67 y.o. lives at 789 Main St
			Contact: 123-456-7890
			Accounts:
			CheckingAccount(accountNumber='8545176546670912', balance=100.00, currency=MDL)
			SavingsAccount(accountNumber='2509546693905327', balance=100.00, currency=USD, interestRate=2.0)
```

## Conclusion:
In summary, incorporating the SOLID principles during the development of a banking system provided me with a structured and effective method. These principles have enhanced the quality of my code and its ease of maintenance, as well as established a strong foundation for future improvements and adjustments. Embracing the SOLID principles has proven to be a worthwhile endeavor in my pursuit of creating a proficient bank, despite the requirement for meticulous planning and initial effort.
