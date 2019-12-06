package bank;

import bank.domain.Account;
import bank.domain.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest
{

    @Mock
    private static Account account;

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
        account.withdrawal(10);
        assertEquals(90,account.getSolde(),0);
    }

    @Test
    public void should_ThrowException_Withdraw_10_From_Account_IfValueNotValid(){

        assertThrows(Exception.class,()->{
            account.withdrawal(150);},
                "value of withdraw should be lower than account's solde and higher to 0");
    }

    @Test
    public void should_AddWithdraw_To_Operations()throws Exception {
        account.withdrawal(100);
        assertEquals(1,account.getStatement().getOperations().size(),0);
    }

    @Test
    public void should_Depose_10_To_Account() throws Exception {
        account.deposit(10);
        assertEquals(110,account.getSolde(),0);
    }

    @Test
    public void should_ThrowException_Depose_10_To_Account(){
        assertThrows(Exception.class,()->{
        account.deposit(-10);},"value of deposit should be higher than 0");
    }

    @Test
    public void should_AddDepose_To_Operations()throws Exception {
        account.deposit(10);
        assertEquals(1,account.getStatement().getOperations().size(),0);
    }

    @Test
    public void should_Transfer_20_To_Other_Account() throws Exception{
        Account toAccount=Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        account.transfer(20,toAccount);

        assertEquals(120,toAccount.getSolde());
        assertEquals(80,account.getSolde());
    }

    @Test
    public void should_FindTransactionInHistory_IfAccountExisted()throws Exception{
        Account toAccount=Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        account.transfer(20,toAccount);
        assertNotEquals(0,account.searchInHistory(toAccount).size());
    }

}
