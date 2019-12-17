package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.Statement;
import bank.model.Transfer;
import java.util.List;
import java.util.stream.Collectors;

public class StatementServiceImpl implements StatementService {

    @Override
    public void addOperation(Operation operation, Statement statement){
        statement.getOperations().add(operation);
    }
    @Override
    public List<Operation> findOperationsByAccount(Account desiredAccount, Statement statement){
       return statement.getOperations().stream()
               .filter(Transfer.class::isInstance)
               .map(Transfer.class::cast)
               .filter(operation -> ((operation.getToAccount() != null && operation.getToAccount()==desiredAccount)
                       || (operation.getFromAccount() != null && operation.getFromAccount()==desiredAccount)))
               .collect(Collectors.toList());
    }
}
