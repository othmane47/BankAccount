package bank.service;

import bank.exception.OperationFailedException;
import bank.model.Account;
import bank.model.Operation;

import java.util.List;

public interface AccountService {
    void withdrawal(double value, Account account) throws OperationFailedException;

    void deposit(double value, Account account) throws OperationFailedException;

    void transfer(double value, Account toAccount, Account fromAccount) throws OperationFailedException;

    List<Operation> searchInHistory(Account desiredAccount, Account fromAccount);
}
