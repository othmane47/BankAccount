package bank.service;

import bank.exception.OperationFailedException;
import bank.model.Account;
import bank.model.Operation;
import bank.model.OperationType;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private StatementServiceImpl statementService = new StatementServiceImpl();
    private OperationServiceImpl operationService = new OperationServiceImpl();


    @Override
    public void withdrawal(double value, Account account) throws OperationFailedException {
        if (value <= 0)
            throw new OperationFailedException("Value of withdraw should be higher than 0");
        if (value > account.getSolde())
            throw new OperationFailedException("Value of withdraw should be less than or equal to account's solde");

        account.setSolde(account.getSolde() - value);
        Operation op = operationService.createOperation("WITHDRAW", value);

        statementService.addOperation(op, account.getStatement());

    }

    @Override
    public void deposit(double value, Account account) throws OperationFailedException {
        if (value <= 0)
            throw new OperationFailedException("Value of deposit should be higher than 0");

        account.setSolde(account.getSolde() + value);
        statementService.addOperation(operationService.createOperation(OperationType.DEPOSIT.toString(), value)
                , account.getStatement());

    }

    @Override
    public void transfer(double value, Account toAccount, Account fromAccount) throws OperationFailedException {
        if (value <= 0)
            throw new OperationFailedException("Value of transfer should be higher than 0");
        if (value > fromAccount.getSolde())
            throw new OperationFailedException("Value of transfer should be less than or equal to account's solde");

        fromAccount.setSolde(fromAccount.getSolde() - value);
        toAccount.setSolde(toAccount.getSolde() + value);

        Operation transfer=operationService.createOperation(OperationType.TRANSFER.toString(), value, fromAccount, toAccount);

        statementService.addOperation(transfer, fromAccount.getStatement());
        statementService.addOperation(transfer, toAccount.getStatement());
    }

    @Override
    public List<Operation> searchInHistory(Account desiredAccount, Account fromAccount) {
        return statementService.findOperationsByAccount(desiredAccount, fromAccount.getStatement());


    }
}
