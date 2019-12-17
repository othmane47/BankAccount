package bank.service;

import bank.exception.OperationFailedException;
import bank.model.Account;
import bank.model.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class  AccountServiceImplTest {

    private static AccountServiceImpl accountService;
    private Account account;

    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {
        accountService = new AccountServiceImpl();
    }

    /**
     * Initialize account.
     */
    @BeforeEach
    public void initAccount() {
        account=Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();
    }

    /**
     * Should throw exception when withdrawing with negative value.
     */
    @Test
    public void should_throwException_whenWithdrawing_withNegativeValue(){

        assertThatThrownBy(() -> accountService.withdrawal(-100, account))
                .isInstanceOf(OperationFailedException.class)
                .hasMessageContaining("Value of withdraw should be higher than 0");

    }

    /**
     * Should throw exception when withdrawing with value higher than solde.
     */
    @Test
    public void should_throwException_whenWithdrawing_withValueHigherThanSolde(){

        assertThatThrownBy(() -> accountService.withdrawal(150, account))
                .isInstanceOf(OperationFailedException.class)
                .hasMessageContaining("Value of withdraw should be less than or equal to account's solde");
    }

    /**
     * Should withdraw from account with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_withdrawFromAccount_withValidValue() throws OperationFailedException {
        accountService.withdrawal(10,account);

        assertThat(account.getSolde()).isEqualTo(90.0);
    }

    /**
     * Should add withdraw operation to history with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_addWithdrawOperationToHistory_withValidValue() throws OperationFailedException {
        accountService.withdrawal(10,account);

        assertThat(account.getStatement().getOperations()).isNotEmpty();
    }

    /**
     * Should throw exception when deposit with negative value.
     */
    @Test
    public void should_throwException_whenDeposit_withNegativeValue(){

        assertThatThrownBy(() -> accountService.deposit(-100, account))
                .isInstanceOf(OperationFailedException.class)
                .hasMessageContaining("Value of deposit should be higher than 0");
    }

    /**
     * Should deposit to account with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_DepositToAccount_withValidValue() throws OperationFailedException {
        accountService.deposit(10, account);

        assertThat(account.getSolde()).isEqualTo(110);
    }

    /**
     * Should add deposit operation to history with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_addDepositOperationToHistory_withValidValue() throws OperationFailedException {
        accountService.deposit(10,account);

        assertThat(account.getStatement().getOperations()).isNotEmpty();
    }

    /**
     * Should throw exception when transferring with negative value.
     */
    @Test
    public void should_throwException_whenTransferring_withNegativeValue(){
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        assertThatThrownBy(() -> accountService.transfer(-100,toAccount, account))
                .isInstanceOf(OperationFailedException.class)
                .hasMessageContaining("Value of transfer should be higher than 0");
    }

    /**
     * Should throw exception when transferring with value higher than solde.
     */
    @Test
    public void should_throwException_whenTransferring_withValueHigherThanSolde(){
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        assertThatThrownBy(() -> accountService.transfer(110,toAccount, account))
                .isInstanceOf(OperationFailedException.class)
                .hasMessageContaining("Value of transfer should be less than or equal to account's solde");
    }

    /**
     * Should transfer to account with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_TransferToAccount_withValidValue() throws OperationFailedException {
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        accountService.transfer(10, toAccount, account);

        assertThat(account.getSolde()).isEqualTo(90);
    }

    /**
     * Should add transfer operation to both accounts histories with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_addTransferOperationToBothAccountsHistories_withValidValue() throws OperationFailedException {
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        accountService.transfer(10, toAccount, account);

        assertThat(toAccount.getStatement().getOperations()).isNotEmpty();

    }

    /**
     * Should find operation in both accounts histories.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_findOperationInBothAccountsHistories_withValidValues() throws OperationFailedException {
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        accountService.transfer(10, toAccount, account);

        assertThat(accountService.searchInHistory(account,toAccount)).isNotEmpty();
    }

}