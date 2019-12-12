package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.factory.OperationFactoy;
import lombok.Data;


public class OperationServiceImpl implements OperationService {

    private OperationFactoy operationFactoy=new OperationFactoy();

    @Override
    public Operation createOperation(String operation, double value, Account from, Account to) {
        return operationFactoy.create(operation,value,from,to);
    }

    @Override
    public Operation createOperation(String operation, double value) {
        return operationFactoy.create(operation,value);
    }
}
