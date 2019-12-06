package bank.domain;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Operation {
    private double amount;
    private LocalDateTime date;
    private Account fromAccount;
    private Account toAccount;
    private OperationType operationType;
}
