package bank;

import bank.exception.OperationFailedException;
import bank.model.Account;
import bank.model.Operation;
import bank.model.Statement;
import bank.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * I used these tests from the beginning of the application by applying the TDD approach to realize the body of the application,
 * after when the architecture of the project has developed in several layers
 * I switched to class tests dedicated to each service to increase code coverage by unit tests
 */
public class AppTest {

    private static Account account;

    private static AccountServiceImpl service;

    @BeforeAll
    private static void setUpService() {
        service = new AccountServiceImpl();
    }

    @BeforeEach
    private void setUp() {
        account = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();
    }

    /**
     * Should find 0 in solde.
     */
    @Test
    public void should_Find_0_In_Solde() {
        account = Account.builder()
                .build();
        assertEquals(0, account.getSolde(), 0.0);
    }

    /**
     * Should init solde by 10.
     */
    @Test
    public void should_Init_Solde_By_10() {
        account = Account.builder()
                .statement(new Statement())
                .solde(10).build();

        assertEquals(10, account.getSolde(), 0.0);
    }

    /**
     * Should withdrawal 10 from account.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_Withdrawal_10_From_Account() throws OperationFailedException {
        service.withdrawal(10, account);
        assertEquals(90, account.getSolde(), 0);
    }

    /**
     * Should throw exception withdraw 10 from account if value not valid.
     */
    @Test
    public void should_ThrowException_Withdraw_10_From_Account_IfValueNotValid() {
        assertThrows(Exception.class, () -> {
                    service.withdrawal(150, account);
                },
                "value of withdraw should be lower than account's solde and higher to 0");
    }

    /**
     * Should add withdraw to operations.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_AddWithdraw_To_Operations() throws OperationFailedException {
        service.withdrawal(100, account);
        assertEquals(1, account.getStatement().getOperations().size(), 0);
    }

    /**
     * Should depose 10 to account.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_Depose_10_To_Account() throws OperationFailedException {
        service.deposit(10, account);
        assertEquals(110, account.getSolde(), 0);
    }

    /**
     * Should throw exception depose 10 to account.
     */
    @Test
    public void should_ThrowException_Depose_10_To_Account() {
        assertThrows(Exception.class, () -> {
            service.deposit(-10, account);
        }, "value of deposit should be higher than 0");
    }

    /**
     * Should add depose to operations.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_AddDepose_To_Operations() throws OperationFailedException {
        service.deposit(10, account);
        for (Operation op : account.getStatement().getOperations())
            System.out.println(op);
        assertEquals(1, account.getStatement().getOperations().size(), 0);
    }

    /**
     * Should transfer 20 to other account.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_Transfer_20_To_Other_Account() throws OperationFailedException {
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        service.transfer(20, toAccount, account);

        assertEquals(120, toAccount.getSolde());
    }

    /**
     * Should find transaction in history if account existed.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_FindTransactionInHistory_IfAccountExisted() throws OperationFailedException {
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        service.transfer(20, toAccount, account);
        assertNotEquals(0, service.searchInHistory(toAccount, account).size());
    }

}
