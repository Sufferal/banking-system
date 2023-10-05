# Topic: Creational Design Patterns

### Course: Software Design Techniques and Mechanisms
### Author: Botnari Ciprian

## Theory
**Creational design patterns** are a category of design patterns that focus on the process of object creation. They provide a way to create objects in a flexible and controlled manner, while decoupling the client code from the specifics of object creation. Creational design patterns address common problems encountered in object creation, such as how to create objects with different initialization parameters, how to create objects based on certain conditions, or how to ensure that only a single instance of an object is created. There are several creational design patterns that have their own strengths and weaknesses. Each of it is best suited for solving specific problems related to object creation. By using creational design patterns, developers can improve the flexibility, maintainability, and scalability of their code.language.
## Objectives
1. Study and understand the Creational Design Patterns.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. Use some creational design patterns for object instantiation in a sample project.

## Creational design patterns

### Singleton
A creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.
```
public class CustomerManager {
  private static CustomerManager instance;
  private List<Customer> customers;

  // Private constructor to prevent external instantiation
  private CustomerManager() {
    this.customers = new ArrayList<>();
  }

  public static synchronized CustomerManager getInstance() {
    if (instance == null) {
      instance = new CustomerManager();
    }
    return instance;
  }

  .....
}
```

### Factory Method
A creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.
```
public interface AccountFactory {
  Account createAccount(double balance, Currency currency);
}

public class CheckingAccountFactory implements AccountFactory {
  @Override
  public Account createAccount(double balance, Currency currency) {
    return new CheckingAccount(balance, currency);
  }
}

public class SavingsAccountFactory implements AccountFactory {
  @Override
  public Account createAccount(double balance, Currency currency) {
    return new SavingsAccount(balance, currency);
  }
}
```

### Builder
A creational design pattern that lets you construct complex objects step by step. The pattern allows you to produce different types and representations of an object using the same construction code.
```
public static class Builder {
    private int customerId;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String phone;
    private List<Account> accounts = new ArrayList<Account>();
    private List<Notification> notifications = new ArrayList<Notification>();

    public Builder(int customerId) {
      this.customerId = customerId;
    }

    public Builder setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder setLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    .....
}
```

### Prototype
A creational design pattern that lets you copy existing objects without making your code dependent on their classes.
```
public interface Offer extends Cloneable {
  Offer clone() throws CloneNotSupportedException;
}

public class DiscountOffer implements Offer {
  private String description;

  public DiscountOffer(String description) {
    this.description = description;
  }

  @Override
  public Offer clone() throws CloneNotSupportedException {
    return (DiscountOffer) super.clone();
  }

  .....
}

public class LoanOffer implements Offer {
  private int amount;
  private Currency currency;

  public LoanOffer(int amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  @Override
  public Offer clone() throws CloneNotSupportedException {
    return (LoanOffer) super.clone();
  }

  .....
}

public class OfferManager {
  private Map<String, Offer> offerPrototypes = new HashMap<>();

  public void addOfferPrototype(String key, Offer prototype) {
    offerPrototypes.put(key, prototype);
  }

  public Offer getOfferClone(String key) throws CloneNotSupportedException {
    Offer prototype = offerPrototypes.get(key);
    if (prototype != null) {
      return prototype.clone();
    }
    return null;
  }
}

```

## Results:
### View customers information:
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
7. Back to main menu
Enter your choice: 1
(Customer Regular) #1 Renala Mureșan, 25 y.o. lives at Str. Mihai Eminescu, 1, Chișinău, Moldova
    Contact: +373 79 000 000
    Accounts:
    CheckingAccount(accountNumber='7152897362261174', balance=12.57, currency=MDL)
    SavingsAccount(accountNumber='7685713583580102', balance=198.00, currency=EUR, interestRate=2.0)
    Notifications:
    (Offer notification): DiscountOffer[ 10% Discount on Loans ]

(Customer Premium) #2 Vasile Ciobanu, 
    Contact: +373 79 000 001
    Accounts:
    CheckingAccount(accountNumber='7465450336210729', balance=134.00, currency=USD)
    SavingsAccount(accountNumber='8917430232801224', balance=953.00, currency=EUR, interestRate=2.0)
    Notifications:
    (SECURITY NOTIFICATION): YOUR ACCOUNT HAS BEEN HACKED :(
    (Offer notification): BonusPointsOffer[ 1000 points ]

(Customer VIP) #3 Radagon
    Accounts:
    CheckingAccount(accountNumber='5994433571899689', balance=1257.00, currency=MDL)
    SavingsAccount(accountNumber='1206225767276792', balance=198.00, currency=EUR, interestRate=2.0)
    CheckingAccount(accountNumber='1994229039553607', balance=134.00, currency=USD)
    SavingsAccount(accountNumber='0460492343118216', balance=953.00, currency=EUR, interestRate=2.0)
    Notifications:
    (Offer notification): LoanOffer[ 1000000 USD ]

(Customer VIP) #4 Ripper
    Accounts:
    CheckingAccount(accountNumber='5308205770069657', balance=4397.00, currency=MDL)
    SavingsAccount(accountNumber='0455721280257745', balance=8932.00, currency=EUR, interestRate=2.0)
    CheckingAccount(accountNumber='0235045959224621', balance=13789.00, currency=USD)
    SavingsAccount(accountNumber='0691858273669835', balance=9823.00, currency=EUR, interestRate=2.0)
    Notifications:
    (Offer notification): LoanOffer[ 1000000 USD ]

(Customer VIP) #5 Zeus
    Accounts:
    CheckingAccount(accountNumber='9298804701852902', balance=3123.00, currency=MDL)
    SavingsAccount(accountNumber='4027797813043186', balance=5235.00, currency=EUR, interestRate=2.0)
    CheckingAccount(accountNumber='9030849051503547', balance=234251.00, currency=USD)
    SavingsAccount(accountNumber='1121819915434219', balance=31235.00, currency=EUR, interestRate=2.0)
    Notifications:
    (SECURITY NOTIFICATION): CHANGE YOUR PIN.
    (Offer notification): LoanOffer[ 1000000 USD ]
```

## Conclusion:
The utilization of creational design patterns such as Singleton, Factory Method, Builder, and Prototype has been instrumental in enhancing the development of the banking system. These design patterns have given me a structured and efficient approach to creating and managing objects within the application, aligning perfectly with the SOLID principles. They have not only improved the quality of the code but have also simplified its maintenance and scalability, setting a robust foundation for future enhancements and adaptations. Embracing these creational design patterns may have required meticulous planning and initial effort, but it has undoubtedly proven to be a valuable investment in our journey toward building a proficient and adaptable banking system.
