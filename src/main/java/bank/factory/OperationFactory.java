package bank.factory;

import bank.model.*;

import java.time.LocalDateTime;

public class OperationFactory implements AbstractFactory<Operation> {

    @Override
    public Operation create(String operation, double value) {
        if(OperationType.DEPOSIT.toString().equalsIgnoreCase(operation))
            return Deposit.builder()
                    .amount(value)
                    .date(LocalDateTime.now())
                    .build();

        if(OperationType.WITHDRAW.toString().equalsIgnoreCase(operation))
            return Withdraw.builder()
                    .amount(value)
                    .date(LocalDateTime.now())
                    .build();
        return null;
    }

    @Override
    public Operation create(String operation, double value, Account from, Account to) {
        if(OperationType.TRANSFER.toString().equalsIgnoreCase(operation))
            return Transfer.builder()
                    .fromAccount(from)
                    .toAccount(to)
                    .amount(value)
                    .date(LocalDateTime.now())
                    .build();
        return null;
    }
}
