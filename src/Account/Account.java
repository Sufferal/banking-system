package Account;

import Bank.Currency;
import ExchangeRate.ExchangeRateProvider;
import ExchangeRate.LocalExchangeRateProvider;
import Transaction.*;
import Transaction.Payment.PaymentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Account implements AccountManagement  {
  private String accountNumber;
  private PaymentType paymentType;
  private double balance;
  private Currency currency;
  private List<Transaction> transactionHistory;

  public Account(double balance, Currency currency) {
    this.accountNumber = this.generateAccountNumber();
    this.paymentType = PaymentType.MODERN;
    this.balance = balance;
    this.currency = currency;
    this.transactionHistory = new ArrayList<>();
  }

  public Account(double balance, Currency currency, PaymentType paymentType) {
    this.accountNumber = this.generateAccountNumber();
    this.paymentType = paymentType;
    this.balance = balance;
    this.currency = currency;
    this.transactionHistory = new ArrayList<>();
  }

  public String getAccountNumber() { return this.accountNumber; }
  public PaymentType getPaymentType() { return this.paymentType; }
  public double getBalance() { return this.balance; }
  public Currency getCurrency() { return this.currency; }
  public List<Transaction> getTransactionHistory() { return this.transactionHistory; }

  public void addTransaction(Transaction transaction) {
    this.transactionHistory.add(transaction);
  }

  public boolean hasSufficientFunds(double amount) {
    return this.balance >= amount;
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

  @Override
  public void deposit(double amount, Currency transactionCurrency) {
    double exchangeRate = 1;

    if (transactionCurrency != this.currency) {
      ExchangeRateProvider exchangeRateProvider = new LocalExchangeRateProvider();
      Currency sourceCurrency = this.currency;
      LocalDate date = LocalDate.now();
      exchangeRate = exchangeRateProvider.getExchangeRate(sourceCurrency, transactionCurrency, date);
    }

    this.balance += amount * exchangeRate;
  }

  @Override
  public void withdraw(double amount, Currency transactionCurrency) {
    double exchangeRate = 1;

    if (transactionCurrency != this.currency) {
      ExchangeRateProvider exchangeRateProvider = new LocalExchangeRateProvider();
      Currency sourceCurrency = this.currency;
      LocalDate date = LocalDate.now();
      exchangeRate = exchangeRateProvider.getExchangeRate(transactionCurrency, sourceCurrency, date);
    }

    this.balance -= amount * exchangeRate;
  }

  @Override
  public String toString() {
    return "(Account) " + accountNumber + " has " + String.format("%.2f", balance) + " " + currency;
  }
}
