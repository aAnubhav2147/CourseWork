package account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
	BankAccount bankAccount;

	@Before
	public void setUp() throws Exception {
		bankAccount = new BankAccount();
	}

	@Test
	public void testDeposit() {
		//fail("Not yet implemented");
		bankAccount.deposit(100);
		assertEquals(bankAccount.balance,100,0.0);
	}

	@Test
	public void testWithdraw() {
		//fail("Not yet implemented");
		bankAccount.deposit(1000);
		bankAccount.withdraw(100);
		bankAccount.deposit(10);
		assertEquals(bankAccount.balance,910,0.0);
	}

}
