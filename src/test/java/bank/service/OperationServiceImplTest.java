package bank.service;

import bank.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;


class OperationServiceImplTest {

    /**
     * The Operation service.
     */
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
    public void shouldSuccess_createDepositOperation(){

        assertNotNull(operationServiceImpl.createOperation("Deposit",100.0));
    }

    /**
     * Should success create transfer operation.
     */
    @Test
    public void shouldSuccess_createTransferOperation(){

        assertNotNull(operationServiceImpl.createOperation("Transfer",100.0,fromAccount,toAccount));
    }


}