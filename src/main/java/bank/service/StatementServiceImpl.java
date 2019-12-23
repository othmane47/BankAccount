package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.Statement;
import bank.model.Transfer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatementServiceImpl implements StatementService {

    @Override
    public void addOperation(Operation operation, Statement statement){
        statement.getOperations().add(operation);
    }
    @Override
    public List<Operation> findOperationsByAccount(Account desiredAccount, Statement statement){
        Stream<Transfer> transferStream = statement.getOperations()
                .stream()
                .filter(Transfer.class::isInstance)
                .map(Transfer.class::cast);
        Stream<Transfer> filteredTransferStream=transferStream
                .filter(operation -> ((operation.getToAccount() != null && operation.getToAccount() == desiredAccount)
                        || (operation.getFromAccount() != null && operation.getFromAccount() == desiredAccount)));

        return filteredTransferStream.collect(Collectors.toList());
    }
}
