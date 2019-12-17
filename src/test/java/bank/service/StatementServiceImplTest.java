package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.Statement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StatementServiceImplTest {

    private static StatementServiceImpl statementServiceImpl;
    private static OperationServiceImpl operationServiceImpl;
    private static Statement statement;
    private Operation operation;

    /**
     * Set up.
     */
    @BeforeAll
    public static void setUp() {
        statementServiceImpl=new StatementServiceImpl();
        operationServiceImpl=new OperationServiceImpl();
        statement=new Statement();
    }

    /**
     * Should add operation to statement.
     */
    @Test
    public void should_AddOperation_ToStatement_whenValidValues(){
        operation =operationServiceImpl.createOperation("Deposit",100.0);

        statementServiceImpl.addOperation(operation,statement);

        assertThat(statement.getOperations()).isNotEmpty();
    }

    /**
     * Should find operation in statement.
     */
    @Test
    public void should_FindOperation_InStatement_whenValidValues(){
        Account fromAccount = Account.builder()
                .statement(statement)
                .solde(200)
                .name("Carlos Ghosn")
                .build();
        Account toAccount = Account.builder()
                .statement(new Statement())
                .solde(150)
                .name("Rita Ghosn")
                .build();

        operation =operationServiceImpl.createOperation("Transfer",100.0,fromAccount,toAccount);
        statementServiceImpl.addOperation(operation,statement);

        assertThat(statementServiceImpl.findOperationsByAccount(toAccount,statement)).isNotEmpty();
    }

}