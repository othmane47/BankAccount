package bank.service;

import bank.model.Account;
import bank.model.Operation;

public interface OperationService {

    Operation createOperation(String operation, double value, Account from, Account to);
    Operation createOperation(String operation,double value);

}
