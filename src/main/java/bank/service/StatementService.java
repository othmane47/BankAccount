package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.Statement;

import java.util.List;

public interface StatementService {
    void addOperation(Operation operation, Statement statement);

    List<Operation> findOperationsByAccount(Account desiredAccount, Statement statement);
}
