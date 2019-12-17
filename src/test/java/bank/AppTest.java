package bank;

import bank.exception.OperationFailedException;
import bank.model.Account;
import bank.model.Statement;
import bank.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

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
                .name("John Rachid")
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
                .statement(new Statement())
                .build();
        assertThat(account.getSolde()).isEqualTo(0);
    }

    /**
     * Should init solde by 10.
     */
    @Test
    public void should_Init_Solde_By_10() {
        account = Account.builder()
                .name("John Rachid")
                .statement(new Statement())
                .solde(10)
                .build();

        assertThat(account.getSolde()).isEqualTo(10);
    }

    /**
     * Should withdrawal 10 from account.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_Withdrawal_10_From_Account() throws OperationFailedException {
        service.withdrawal(10, account);
        assertThat(account.getSolde()).isEqualTo(90);
    }

    /**
     * Should throw exception withdraw 10 from account if value not valid.
     */
    @Test
    public void should_ThrowException_Withdraw_10_From_Account_WhenValueNotValid() {
        assertThatThrownBy(() -> service.withdrawal(150, account))
                .isInstanceOf(OperationFailedException.class)
                .hasMessageContaining("Value of withdraw should be less than or equal to account's solde");
    }

    /**
     * Should add withdraw to operations.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_AddWithdraw_To_Operations() throws OperationFailedException {
        service.withdrawal(100, account);
        assertThat(account.getStatement().getOperations()).isNotEmpty();
    }

    /**
     * Should depose 10 to account.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_Depose_10_To_Account() throws OperationFailedException {
        service.deposit(10, account);
        assertThat(account.getSolde()).isEqualTo(110);
    }

    /**
     * Should throw exception depose 10 to account.
     */
    @Test
    public void should_ThrowException_Depose_10_To_Account() {
        assertThatThrownBy(() -> service.deposit(-10, account))
                .isInstanceOf(OperationFailedException.class)
                .hasMessageContaining("Value of deposit should be higher than 0");
    }

    /**
     * Should add depose to operations.
     *
     * @throws Exception the exception
     */
    @Test
    public void should_AddDepose_To_Operations() throws OperationFailedException {
        service.deposit(10, account);

        assertThat(account.getStatement().getOperations()).isNotEmpty();
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
                .name("Johnny hallyday")
                .solde(100)
                .build();

        service.transfer(20, toAccount, account);

        assertThat(toAccount.getSolde()).isEqualTo(120);
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
                .name("Johnny hallyday")
                .solde(100)
                .build();

        service.transfer(20, toAccount, account);

        assertThat(service.searchInHistory(toAccount, account)).isNotEmpty();
    }

}
