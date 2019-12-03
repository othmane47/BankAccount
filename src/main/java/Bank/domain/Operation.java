package Bank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operation {
    private double amount;
    private LocalDateTime date;
    private Account fromAccount;
    private Account toAccount;
    private OperationType operationType;
}
