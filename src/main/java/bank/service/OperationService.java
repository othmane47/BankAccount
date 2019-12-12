package bank.service;

import bank.model.Account;
import bank.model.Operation;

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

}
