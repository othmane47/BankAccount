package bank.service;

import bank.model.*;
import java.util.List;

/**
 * The interface Statement service.
 */
public interface StatementService {

    /**
     * Add operation to statement.
     *
     * @param operation the operation
     * @param statement the statement
     */
    void addOperation(Operation operation, Statement statement);

    /**
     * Find operations by account .
     *
     * @param desiredAccount the desired account
     * @param statement      the statement
     * @return the list
     */
    List<Operation> findOperationsByAccount(Account desiredAccount, Statement statement);
}
