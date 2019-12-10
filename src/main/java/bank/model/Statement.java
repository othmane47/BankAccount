package bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {

    private List<Operation> operations=new ArrayList<>();

    public void addOperation(Operation op){
        operations.add(op);
    }


}
