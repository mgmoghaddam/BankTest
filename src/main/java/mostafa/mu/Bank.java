package mostafa.mu;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import mostafa.mu.models.Account;
import mostafa.mu.models.Currency;
import mostafa.mu.models.Transaction;
import mostafa.mu.models.TransactionType;
import mostafa.mu.models.User;


public class Bank {

  static Scanner sc = new Scanner(System.in);
  static HashMap<String, Account> accounts = new HashMap<>();
  static Account currentAccount = new Account();


  //create account
  public void createAccount() {
    Account account = new Account();
    System.out.print("Enter Account No: ");
    account.setAccno(sc.next());

    //set currency
    System.out.println("Enter The type of your currency: ");
    showCurrencies();
    account.setCurrency(Currency.valueOf(sc.next().toUpperCase()));

    //create User.java
    User user = new User();
    System.out.println("Please enter your first name: ");
    user.setFirstName(sc.next());
    System.out.println("Please enter your last name: ");
    user.setLastName(sc.next());
    System.out.println("Please enter your phone number: ");
    user.setPhoneNumber(sc.next());
    account.setUser(user);

    System.out.println("Enter balance (it must br numbers): ");
    account.setBalance(sc.nextLong());

    account.setTransactions(new ArrayList<>());

    createAccount(account);
    System.out.println(account);
  }

  Account createAccount(Account account) {
    accounts.put(account.getAccno(), account);
    currentAccount = account;
    return account;
  }


  //display account
  public void showAccount(Account ac) {
    if (ac != null) {
      System.out.println(
          "Name of account owner: " + ac.getUser().getFirstName() + " " + ac.getUser()
              .getLastName()
              + "\n" +
              "Phone number: " + ac.getUser().getPhoneNumber()
              + "\n" +
              "Account Number: " + ac.getAccno()
              + "\n" +
              "Currency type: " + ac.getCurrency()
              + "\n" +
              "Total Balance :" + ac.getBalance()
      );
    } else {
      accounts.forEach((k, account) -> System.out.println(
          "Name of account owner: " + account.getUser().getFirstName() + " " + account.getUser()
              .getLastName()
              + "\n" +
              "Phone number: " + account.getUser().getPhoneNumber()
              + "\n" +
              "Account Number: " + account.getAccno()
              + "\n" +
              "Currency type: " + account.getCurrency()
              + "\n" +
              "Total Balance :" + account.getBalance()
      ));
    }
  }

  //deposit money
  public void deposit() {
    long amount;
    System.out.println(
        "Enter the amount you want to deposit in " + currentAccount.getCurrency() + ": ");
    amount = sc.nextLong();
    depositing(currentAccount, amount);
    System.out.println(
        "Your balance is: " + currentAccount.getBalance() + " " + currentAccount.getCurrency());
  }

  void depositing(Account account, long amount) {
    account.setBalance(account.getBalance() + amount);
  }

  //withdraw money
  public void withdrawal() {
    long amount;
    System.out.println("Enter the amount you want to withdraw: ");
    amount = sc.nextLong();
    if (currentAccount.getBalance() >= amount) {
      withdrawing(currentAccount, amount);
      System.out.println("Balance after withdrawal: " + currentAccount.getBalance());
    } else {
      System.out.println("Your balance is less than " + amount + "\tTransaction failed...!!");
    }
  }

  void withdrawing(Account account, long amount) {
    long balance = account.getBalance();
    balance = balance - amount;
    account.setBalance(balance);
  }

  //transfer the money
  public void transfer() {
    String destAccno;
    long amount;
    List<Transaction> srcTransactions = accounts.get(currentAccount.getAccno()).getTransactions();
    long currencyChange = 1;
    System.out.println("Please enter destination accno: ");
    destAccno = sc.next();
    if (accounts.containsKey(destAccno)) {
      List<Transaction> dstTransactions = accounts.get(destAccno).getTransactions();
      System.out.println("Please enter amount of transfer: ");
      amount = sc.nextLong();
      if (currentAccount.getBalance() >= amount) {
        if (!currentAccount.getCurrency().equals(accounts.get(destAccno).getCurrency())) {
          System.out.println(
              "Every " + currentAccount.getCurrency() + " equals ...... " + accounts.get(destAccno)
                  .getCurrency() + ": ");
          currencyChange = sc.nextLong();
        }
        withdrawing(currentAccount, amount);
        depositing(accounts.get(destAccno), amount * currencyChange);
        long time = System.currentTimeMillis();
        srcTransactions.add(Transaction.builder()
            .amount(amount)
            .dateTime(time)
            .destAccno(destAccno)
            .transactionType(TransactionType.TRANSFER).build());
        accounts.get(currentAccount.getAccno()).setTransactions(srcTransactions);
        dstTransactions.add(Transaction.builder()
            .amount(amount)
            .dateTime(time)
            .srcAccno(currentAccount.getAccno())
            .transactionType(TransactionType.TRANSFER).build());
        accounts.get(destAccno).setTransactions(dstTransactions);
        System.out.println("transaction done. \n " + amount + " " + currentAccount.getCurrency()
            + " transferred.");
      } else {
        System.out.println("Your balance is less than " + amount + "\tTransaction failed...!!");
      }
    } else {
      System.out.println("Destination account is not exist.");
    }
  }

  //method to search an account with acco
  public Boolean getAccount(String accno) {
    if (accounts.containsKey(accno)) {
      currentAccount = accounts.get(accno);
      showAccount(currentAccount);
      return true;
    } else {
      return false;
    }
  }

  private void showCurrencies() {
    for (Currency currency : EnumSet.allOf(Currency.class)) {
      System.out.println(currency);
    }
  }

}
