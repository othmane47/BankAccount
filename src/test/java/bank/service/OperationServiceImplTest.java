package bank.service;

import bank.model.Account;
import bank.model.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.*;


class OperationServiceImplTest {

    private OperationServiceImpl operationServiceImpl;
    @Mock
    private Account fromAccount;
    @Mock
    private Account toAccount;

    /**
     * Set up.
     */
    @BeforeEach
    public void setUp() {
        operationServiceImpl=new OperationServiceImpl();

    }

    /**
     * Should success create deposit operation.
     */
    @Test
    public void shouldSuccess_createDepositOperation_whenValidValues(){

        assertThat(operationServiceImpl.createOperation("Deposit",100.0)).isNotNull();
    }
    /**
     * Should success create withdraw operation.
     */
    @Test
    public void shouldSuccess_createWithdrawOperation_whenValidValues(){

        assertThat(operationServiceImpl.createOperation("Withdraw",10.0)).isNotNull();
    }

    /**
     * Should success create transfer operation.
     */
    @Test
    public void shouldSuccess_createTransferOperation_whenValidValues(){

        assertThat(operationServiceImpl.createOperation("Transfer",100.0,fromAccount,toAccount)).isNotNull();
    }

    @Test
    public void shouldSuccess_printingMessage_whenValidValues(){
        Operation op=operationServiceImpl.createOperation("Withdraw",10.0);

        assertThat(op.print()).contains("Withdraw 10.0€ on");

    }

}