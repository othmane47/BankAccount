package bank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

    private double solde;
    private Statement statement;

}
