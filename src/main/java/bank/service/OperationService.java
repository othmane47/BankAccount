package bank.service;

import bank.model.Account;
import bank.model.Operation;

/**
 * The interface Operation service.
 */
public interface OperationService {

    /**
     * Create transfer operation
     *
     * @param operation the operation type
     * @param value     the value of amount
     * @param from      the from account
     * @param to        the to
     * @return the operation
     */
    Operation createOperation(String operation, double value, Account from, Account to);

    /**
     * Create deposit/withdraw operation.
     *
     * @param operation the operation type
     * @param value     the value of amount
     * @return the operation
     */
    Operation createOperation(String operation,double value);


    /**
     * Print operation details.
     *
     * @param operation the operation
     */
    String printOperation(Operation operation);

}
