package bank.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@Slf4j
public class Withdraw implements Operation {
    private Double amount;
    private LocalDateTime date;

    @Override
    public String print() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String message="Withdraw "+amount+"€ on "+date.format(formatter);
        log.info(message);
        return message;
    }
}
