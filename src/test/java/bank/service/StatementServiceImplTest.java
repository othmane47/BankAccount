package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;


class StatementServiceImplTest {

    private static StatementServiceImpl statementServiceImpl;
    private static Statement statement;
    @Mock
    private Operation operation;
    @Mock
    private Account account;

    /**
     * Set up.
     */
    @BeforeAll
    public static void setUp() {
        statementServiceImpl=new StatementServiceImpl();
        statement=new Statement();
    }

    /**
     * Should add operation to statement.
     */
    @Test
    public void should_AddOperation_ToStatement(){
        statementServiceImpl.addOperation(operation,statement);
        assertEquals(1,statement.getOperations().size());
    }

    /**
     * Should find operation instatement.
     */
    @Test
    public void should_FindOperation_Instatement(){

        statementServiceImpl.addOperation(operation,statement);
        assertNotNull(statementServiceImpl.findOperationsByAccount(account,statement));

    }

}