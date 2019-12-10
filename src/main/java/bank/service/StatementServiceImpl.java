package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class StatementServiceImpl implements StatementService {
    @Override
    public void addOperation(Operation operation, Statement statement){
        statement.addOperation(operation);
    }
    @Override
    public List<Operation> findOperationsByAccount(Account desiredAccount, Statement statement){
       return statement.getOperations()
                .stream()
                .filter(operation -> ((operation.getToAccount() != null && operation.getToAccount().equals(desiredAccount))
                        || (operation.getFromAccount() != null && operation.getFromAccount().equals(desiredAccount))))
                .collect(Collectors.toList());

    }
}
