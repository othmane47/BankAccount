package Bank.domain;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Account {

    private double solde;
    private Statement statement;

    public void withdrawal(double value) throws Exception {
        if(value<=0)
            throw new Exception("value of withdraw should be higher than 0");
        if(value>solde)
            throw new Exception("value of transfer should be less than or equal to account's solde");
        solde-=value;
        Operation op = Operation.builder()
                .amount(value)
                .date(LocalDateTime.now())
                .operationType(OperationType.WITHDRAW)
                .build();
        statement.addOperation(op);
    }

    public void deposit(double value) throws Exception{
        if(value>=0) {
            solde += value;
            Operation op = Operation.builder()
                    .amount(value)
                    .date(LocalDateTime.now())
                    .operationType(OperationType.DEPOSIT)
                    .build();
            statement.addOperation(op);
        }
        else
            throw new Exception("value of deposit should be higher than 0");
    }

    public void transfer(double value,Account toAccount) throws Exception{
        if(value<=0)
            throw new Exception("Value of withdraw should be higher than 0");
        if(value>solde)
            throw new Exception("Value of transfer should be less than or equal to account's solde");

        solde-=value;
        toAccount.solde+=value;

        statement.addOperation(Operation.builder()
                .amount(value)
                .date(LocalDateTime.now())
                .toAccount(toAccount)
                .operationType(OperationType.TRANSFER)
                .build()
        );
        toAccount.statement.addOperation(Operation.builder()
                .amount(value)
                .date(LocalDateTime.now())
                .fromAccount(this)
                .operationType(OperationType.TRANSFER)
                .build());
    }

    public List<Operation> searchInHistory(Account desiredAccount){
        if(statement.getOperations()!=null) {
            List<Operation> desiredOperations = statement.getOperations()
                    .stream()
                    .filter(operation -> ((operation.getToAccount() != null && operation.getToAccount().equals(desiredAccount))
                            || (operation.getFromAccount() != null && operation.getFromAccount().equals(desiredAccount))))
                    .collect(Collectors.toList());
            return desiredOperations;
        }
        else return null;
    }
}
