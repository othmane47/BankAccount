package bank.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Statement {

    private List<Operation> operations=new ArrayList<>();

}
