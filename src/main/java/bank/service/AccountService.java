package bank.service;

import bank.model.Account;
import bank.model.Operation;

import java.util.List;

public interface AccountService {
    void withdrawal(double value, Account account) throws Exception;

    void deposit(double value, Account account) throws Exception;

    void transfer(double value, Account toAccount, Account fromAccount) throws Exception;

    List<Operation> searchInHistory(Account desiredAccount, Account fromAccount);
}
