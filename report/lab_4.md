# Topic: Behavioral Design Patterns

### Course: Software Design Techniques and Mechanisms
### Author: Botnari Ciprian

## Theory
**Behavioral design patterns** are a category of design patterns that focus on the interaction and communication between objects and classes. They provide a way to organize the behavior of objects in a way that is both flexible and reusable, while separating the responsibilities of objects from the specific implementation details. Behavioral design patterns address common problems encountered in object behavior, such as how to define interactions between objects, how to control the flow of messages between objects, or how to define algorithms and policies that can be reused across different objects and classes.

Some examples of from this category of design patterns are:

* Chain of Responsibility
* Command
* Interpreter
* Iterator
* Mediator
* Observer
* Strategy

## Objectives
1. Study and understand the Behavioral Design Patterns.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. The implemented design pattern should help to perform the tasks involved in the system.
   The behavioral DPs can be integrated into the functionalities alongside the structural ones.

## Behavioral design patterns

### Iterator
Lets you traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.).
```
public interface Iterator {
  boolean hasNext();
  Object next();
}

public class CustomerIterator implements Iterator {
  List<Customer> customers;
  int index = 0;

  public CustomerIterator(List<Customer> customers) {
    this.customers = customers;
  }

  @Override
  public boolean hasNext() {
    return index < customers.size();
  }

  @Override
  public Object next() {
    if (this.hasNext()) {
      return customers.get(index++);
    }
    return null;
  }
}
```

### Strategy
Lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.
```
public interface Authentication {
  boolean authenticate();
}

public class PINAuthentication implements Authentication {
  private String userPIN;

  public PINAuthentication(String pin) {
    this.userPIN = pin;
  }

  @Override
  public boolean authenticate() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your PIN: ");
    String pin = scanner.nextLine();
    return pin.equals(userPIN);
  }
}

public class AuthenticationContext {
  private Authentication authentication;

  public AuthenticationContext(Authentication authentication) {
    this.authentication = authentication;
  }

  public boolean authenticate() {
    return authentication.authenticate();
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }
}
```

### Observer
Lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
```
public interface OfferObserver {
  void update(Offer offer);
}

public interface OfferSubject {
  void registerObserver(OfferObserver observer);
  void removeObserver(OfferObserver observer);
  void notifyObservers(Offer offer);
}

public class Offer {
  private String offerDetails;

  public Offer(String offerDetails) {
    this.offerDetails = offerDetails;
  }

  public String getOfferDetails() {
    return this.offerDetails;
  }

  public void setOfferDetails(String offerDetails) {
    this.offerDetails = offerDetails;
  }
}
```

## Results:
### Authentication Strategies
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
7. Publish an offer
8. Create managers
9. Test authentications
10. Back to main menu
Enter your choice: 9
Scanning face...
Face authentication failed. Please try again.
false
Scanning fingerprint...
Fingerprint authentication successful.
true
```

## Conclusion:
The integration of behavioral design patterns like Iterator, Strategy, and Observer has enriched my banking system with enhanced data traversal, dynamic algorithm selection, and real-time customer interaction. These patterns have streamlined operations, increased flexibility, and improved user experience. While their implementation demanded careful consideration, the long-term benefits in terms of code efficiency, adaptability, and customer engagement have made them invaluable for building a robust and user-centric banking system.