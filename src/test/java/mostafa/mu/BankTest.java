package mostafa.mu;


import java.util.ArrayList;
import mostafa.mu.models.Account;
import mostafa.mu.models.Currency;
import mostafa.mu.models.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankTest {

  Bank bank = new Bank();

  static Account testAccount;

  @BeforeClass
  public static void prepare() {
    Account account = Account.builder()
        .accno("1")
        .currency(Currency.DOLLAR)
        .user(User.builder()
            .firstName("a")
            .lastName("b")
            .phoneNumber("0000").build())
        .balance(10000)
        .transactions(new ArrayList<>())
        .build();
    testAccount = account;
  }

  @Test
  public void testCreateAccount() {
    bank.createAccount(testAccount);
    Assert.assertEquals(testAccount, Bank.accounts.get(testAccount.getAccno()));
  }

  @Test
  public void testDeposit() {
    if (Bank.accounts.isEmpty()) {
      testCreateAccount();
    }
    bank.depositing(testAccount, 500);
    Assert.assertEquals(testAccount.getBalance(),
        Bank.accounts.get(testAccount.getAccno()).getBalance());
  }

  @Test
  public void testWithdraw() {
    if (Bank.accounts.isEmpty()) {
      testCreateAccount();
    }
    bank.withdrawing(testAccount, 200);
    Assert.assertEquals(testAccount.getBalance(),
        Bank.accounts.get(testAccount.getAccno()).getBalance());
  }

}
