package mostafa.mu;

import java.util.Scanner;

public class MainBanking {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    //create initial accounts
    System.out.print("How many number of customers do you want to input? ");
    int n = sc.nextInt();
    Bank C = new Bank();
    for (int i = 0; i < n; i++) {
      C = new Bank();
      C.createAccount();
    }
    // loop runs until number 5 is not pressed to exit
    int ch;
    do {
      System.out.println("\n ***Banking System Application***");
      System.out.println(
          " 1. Display all account details \n 2. Search by Account number\n 3. Deposit the amount \n 4. Withdraw the amount \n 5. Transfer \n 6. Exit ");
      System.out.println("Enter your choice: ");
      ch = sc.nextInt();
      switch (ch) {
        case 1:
          C.showAccount(null);
          break;
        case 2:
          System.out.print("Enter account no. you want to search: ");
          String ac_no = sc.next();
          boolean found;
          found = C.getAccount(ac_no);
          if (found) {
            break;
          } else {
            System.out.println("Search failed! Account doesn't exist..!!");
          }
          break;
        case 3:
          System.out.print("Enter Account no. : ");
          ac_no = sc.next();
          found = C.getAccount(ac_no);
          if (found) {
            C.deposit();
            break;
          }
          if (!found) {
            System.out.println("Search failed! Account doesn't exist..!!");
          }
          break;
        case 4:
          System.out.print("Enter Account No : ");
          ac_no = sc.next();
          found = C.getAccount(ac_no);
          if (found) {
            C.withdrawal();
            break;
          }
          if (!found) {
            System.out.println("Search failed! Account doesn't exist..!!");
          }
          break;
        case 5:
          System.out.print("Enter Account No : ");
          ac_no = sc.next();
          found = C.getAccount(ac_no);
          if (found) {
            C.transfer();
            break;
          }
          if (!found) {
            System.out.println("Search failed! Account doesn't exist..!!");
          }
          break;
        case 6:
          System.out.println("See you soon...");
          break;
      }
    }
    while (ch != 6);
  }

}


