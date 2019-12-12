package bank.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Withdraw implements Operation {
    private double amount;
    private LocalDateTime date;
}
