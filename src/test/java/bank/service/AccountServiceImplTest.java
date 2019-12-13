package bank.service;

import bank.exception.OperationFailedException;
import bank.model.Account;
import bank.model.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertEquals("Value of withdraw should be higher than 0", assertThrows(Exception.class, () -> {
            accountService.withdrawal(-100, account);}).getMessage());
    }

    /**
     * Should throw exception when withdrawing with value higher than solde.
     */
    @Test
    public void should_throwException_whenWithdrawing_withValueHigherThanSolde(){

        assertEquals("Value of withdraw should be less than or equal to account's solde", assertThrows(Exception.class, () -> {
            accountService.withdrawal(150, account);}).getMessage());
    }

    /**
     * Should withdraw from account with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_withdrawFromAccount_withValidValue() throws OperationFailedException {
        accountService.withdrawal(10,account);
        assertEquals(90, account.getSolde());
    }

    /**
     * Should add withdraw operation to history with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_addWithdrawOperationToHistory_withValidValue() throws OperationFailedException {
        accountService.withdrawal(10,account);
        assertEquals(1, account.getStatement().getOperations().size());
    }

    /**
     * Should throw exception when deposit with negative value.
     */
    @Test
    public void should_throwException_whenDeposit_withNegativeValue(){

        assertEquals("Value of deposit should be higher than 0", assertThrows(Exception.class, () -> {
            accountService.deposit(-100, account);}).getMessage());
    }

    /**
     * Should deposit to account with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_DepositToAccount_withValidValue() throws OperationFailedException {
        accountService.deposit(10, account);
        assertEquals(110, account.getSolde(), 0);
    }

    /**
     * Should add deposit operation to history with valid value.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_addDepositOperationToHistory_withValidValue() throws OperationFailedException {
        accountService.deposit(10,account);
        assertEquals(1, account.getStatement().getOperations().size());
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
        assertEquals("Value of transfer should be higher than 0", assertThrows(Exception.class, () -> {
            accountService.transfer(-100,toAccount, account);}).getMessage());

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
        assertEquals("Value of transfer should be less than or equal to account's solde", assertThrows(Exception.class, () -> {
            accountService.transfer(110,toAccount, account);}).getMessage());

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
        assertEquals(90, account.getSolde());
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
        assertEquals(1, toAccount.getStatement().getOperations().size());
    }

    /**
     * Should find operation in both accounts histories.
     *
     * @throws OperationFailedException the operation failed exception
     */
    @Test
    public void should_findOperationInBothAccountsHistories() throws OperationFailedException {
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();
        accountService.transfer(10, toAccount, account);
        assertEquals(1,accountService.searchInHistory(account,toAccount).size());
    }

}