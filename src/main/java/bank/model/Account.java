package bank.model;


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

}
