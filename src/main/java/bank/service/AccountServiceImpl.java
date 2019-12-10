package bank.service;

import bank.model.Account;
import bank.model.Operation;
import bank.model.OperationType;
import java.time.LocalDateTime;
import java.util.List;

public class AccountServiceImpl implements AccountService{

    private StatementServiceImpl statementService;

    public AccountServiceImpl(StatementServiceImpl statementService){
        this.statementService=statementService;
    }

    @Override
    public void withdrawal(double value,Account account) throws Exception {
        if(value<=0)
            throw new Exception("value of withdraw should be higher than 0");
        if(value>account.getSolde())
            throw new Exception("value of transfer should be less than or equal to account's solde");
        account.setSolde(account.getSolde()-value);

        Operation op = Operation.builder()
                .amount(value)
                .date(LocalDateTime.now())
                .operationType(OperationType.WITHDRAW)
                .build();

        statementService.addOperation(op,account.getStatement());

    }
    @Override
    public void deposit(double value,Account account) throws Exception{
        if(value>=0) {
            account.setSolde(account.getSolde()+value);
            Operation op = Operation.builder()
                    .amount(value)
                    .date(LocalDateTime.now())
                    .operationType(OperationType.DEPOSIT)
                    .build();

            statementService.addOperation(op,account.getStatement());
        }
        else
            throw new Exception("value of deposit should be higher than 0");
    }
    @Override
    public void transfer(double value, Account toAccount,Account fromAccount) throws Exception{
        if(value<=0)
            throw new Exception("Value of withdraw should be higher than 0");
        if(value>fromAccount.getSolde())
            throw new Exception("Value of transfer should be less than or equal to account's solde");

        fromAccount.setSolde(fromAccount.getSolde()-value);
        toAccount.setSolde(toAccount.getSolde()+value);

        statementService.addOperation(Operation.builder()
                .amount(value)
                .date(LocalDateTime.now())
                .toAccount(toAccount)
                .operationType(OperationType.TRANSFER)
                .build(),
                fromAccount.getStatement());

        statementService.addOperation(Operation.builder()
                .amount(value)
                .date(LocalDateTime.now())
                .fromAccount(fromAccount)
                .operationType(OperationType.TRANSFER)
                .build(),
                toAccount.getStatement());


    }
    @Override
    public List<Operation> searchInHistory(Account desiredAccount,Account fromAccount){
        return statementService.findOperationsByAccount(desiredAccount,fromAccount.getStatement());


    }
}
