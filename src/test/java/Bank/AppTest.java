package Bank;



import Bank.domain.Account;
import Bank.domain.Statement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class AppTest 
{

    private Account account;

    @Test
    public void shouldAnswerWithTrue()
    {

        assertTrue( true );
    }


    @Test
    public void should_find_0_in_solde(){
        account =Account.builder().build();
        assertEquals(account.getSolde(),0,0.0);
    }

    @Test
    public void should_init_solde_by_10(){
        account =Account.builder()
                .statement(new Statement())
                .solde(10).build();
        assertEquals(account.getSolde(),10,0.0);
    }

    @Test
    public void should_Withdrawal_10_From_Account() throws Exception {
        account =Account.builder()
                .statement(new Statement())
                .solde(10).build();
        account.withdrawal(10);
        assertEquals(account.getSolde(),0,0.0);
    }

    @Test
    public void should_ThrowException_Withdraw_10_From_Account(){
        account =Account.builder()
                .statement(new Statement())
                .solde(10).build();
        assertThrows(Exception.class,()->{
            account.withdrawal(15);},
                "value of withdraw should be lower than account's solde and higher to 0");
    }

    @Test
    public void should_Add_Withdrawal_Operations()throws Exception {
        account =Account.builder()
                .statement(new Statement())
                .solde(10).build();
        account.withdrawal(10);
        assertEquals(1,account.getStatement().getOperations().size(),0);
    }


    @Test
    public void should_Depose_10_To_Account() throws Exception {
        account =new Account();
        account.deposit(10);
        assertEquals(10,account.getSolde(),0.0);
    }
    @Test
    public void should_ThrowException_Depose_10_To_Account(){
        account =new Account();
        assertThrows(Exception.class,()->{
        account.deposit(-10);},"value of deposit should be higher than 0");
    }

    @Test
    public void should_Add_Depose_Operations()throws Exception {
        account =new Account();
        account.deposit(10);
        assertEquals(1,account.getStatement().getOperations().size(),0);
    }

    @Test
    public void should_transfer_10_to_other_account() throws Exception{

        Account toAccount=Account.builder()
                .statement(new Statement())
                .solde(100)
                .build();

        Account fromAccount=Account.builder()
                .statement(new Statement())
                .solde(1000)
                .build();

        fromAccount.transfer(200,toAccount);

        assertEquals(300,toAccount.getSolde());
        assertEquals(800,fromAccount.getSolde());
    }



}
