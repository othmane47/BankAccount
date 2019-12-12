package bank.service;

import bank.exception.OperationFailedException;
import bank.model.Account;
import bank.model.Operation;
import java.util.List;

public interface AccountService {

    /**
     * Withdrawal a valid amount from an account.
     *
     * @param value   the value of amount
     * @param account the account
     * @throws OperationFailedException the operation failed exception
     */
    void withdrawal(double value, Account account) throws OperationFailedException;

    /**
     * Deposit a valid amount in an account.
     *
     * @param value   the value of amount
     * @param account the account
     * @throws OperationFailedException the operation failed exception
     */
    void deposit(double value, Account account) throws OperationFailedException;

    /**
     * Transfer a valid amount from an account to other one.
     *
     * @param value       the value of amount
     * @param toAccount   the to account
     * @param fromAccount the from account
     * @throws OperationFailedException the operation failed exception
     */
    void transfer(double value, Account toAccount, Account fromAccount) throws OperationFailedException;

    /**
     * Search in history list.
     *
     * @param desiredAccount the desired account
     * @param fromAccount    the from account
     * @return the list
     */
    List<Operation> searchInHistory(Account desiredAccount, Account fromAccount);
}
