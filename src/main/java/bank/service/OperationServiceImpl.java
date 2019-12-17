package bank.service;

import bank.model.Operation;
import bank.model.Account;
import bank.model.factory.OperationFactory;

public class OperationServiceImpl implements OperationService {

    private OperationFactory operationFactory=new OperationFactory();

    @Override
    public Operation createOperation(String operation, double value, Account from, Account to) {
        return operationFactory.create(operation,value,from,to);
    }

    @Override
    public Operation createOperation(String operation, double value) {
        return operationFactory.create(operation,value);
    }

    @Override
    public String printOperation(Operation operation) {
       return operation.print();
    }
}
