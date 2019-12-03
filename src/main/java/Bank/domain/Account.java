package Bank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Account {
    private double solde;
    private Statement statement;

    public Account(){
        this.solde=0;
        this.statement=new Statement();
    }

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
            throw new Exception("value of withdraw should be higher than 0");
        if(value>solde)
            throw new Exception("value of transfer should be less than or equal to account's solde");

        solde-=value;
        toAccount.solde+=value;

        this.statement.addOperation(Operation.builder()
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
}
