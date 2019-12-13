package bank.model.factory;

import bank.model.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;

class OperationFactoryTest {

    private static OperationFactory factory;
    @Mock
    private Account toAccount;
    @Mock
    private Account fromAccount;

    /**
     * Sets up.
     */
    @BeforeAll
    public static void setUp() {

        factory=new OperationFactory();
    }

    /**
     * Should create deposit operation.
     */
    @Test
    public void should_CreateDepositOperation(){
        assertNotNull(factory.create("Deposit",10));
    }

    /**
     * Should create withdraw operation.
     */
    @Test
    public void should_CreateWithdrawOperation(){
        assertNotNull(factory.create("Withdraw",10));
    }

    /**
     * Should create transfer operation.
     */
    @Test
    public void should_CreateTransferOperation(){
        assertNotNull(factory.create("transfer",10,toAccount,fromAccount));
    }

}