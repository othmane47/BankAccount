package bank;

import bank.model.Account;
import bank.model.Statement;
import bank.service.AccountServiceImpl;
import bank.service.StatementServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{

    private static Account account;

    private static AccountServiceImpl service;

    @BeforeAll
    private static void setUpService(){
        service=new AccountServiceImpl(new StatementServiceImpl());
    }

    @BeforeEach
    private void setUp(){
        account=Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();
    }

    @Test
    public void should_Find_0_In_Solde(){
        account =Account.builder()
                .build();
        assertEquals(0,account.getSolde(),0.0);
    }

    @Test
    public void should_Init_Solde_By_10(){
        account =Account.builder()
                .statement(new Statement())
                .solde(10).build();

        assertEquals(10,account.getSolde(),0.0);
    }

    @Test
    public void should_Withdrawal_10_From_Account() throws Exception {
        service.withdrawal(10,account);
        assertEquals(90,account.getSolde(),0);
    }

    @Test
    public void should_ThrowException_Withdraw_10_From_Account_IfValueNotValid(){
        assertThrows(Exception.class,()->{
                    service.withdrawal(150,account);},
                "value of withdraw should be lower than account's solde and higher to 0");
    }

    @Test
    public void should_AddWithdraw_To_Operations()throws Exception {
        service.withdrawal(100,account);
        assertEquals(1,account.getStatement().getOperations().size(),0);
    }

    @Test
    public void should_Depose_10_To_Account() throws Exception {
        service.deposit(10,account);
        assertEquals(110,account.getSolde(),0);
    }

    @Test
    public void should_ThrowException_Depose_10_To_Account(){
        assertThrows(Exception.class,()->{
            service.deposit(-10,account);},"value of deposit should be higher than 0");
    }

    @Test
    public void should_AddDepose_To_Operations()throws Exception {
        service.deposit(10,account);
        assertEquals(1,account.getStatement().getOperations().size(),0);
    }

    @Test
    public void should_Transfer_20_To_Other_Account() throws Exception{
        Account toAccount=Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        service.transfer(20,toAccount,account);

        assertEquals(120,toAccount.getSolde());
        assertEquals(80,account.getSolde());
    }

    @Test
    public void should_FindTransactionInHistory_IfAccountExisted()throws Exception{
        Account toAccount=Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        service.transfer(20,toAccount,account);
        assertNotEquals(0,service.searchInHistory(toAccount,account).size());
    }

}
