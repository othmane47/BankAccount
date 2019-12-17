package bank.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@Slf4j
public class Transfer implements Operation {

    private Double amount;
    private LocalDateTime date;
    private Account fromAccount;
    private Account toAccount;

    @Override
    public String print() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String message="Transfer "+amount+"€ from "+fromAccount.getName()+" to "+toAccount.getName()+" on "+date.format(formatter);
        log.info(message);
        return message;
    }
}
