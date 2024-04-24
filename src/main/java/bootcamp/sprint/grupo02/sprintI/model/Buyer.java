package bootcamp.sprint.grupo02.sprintI.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {

    private int id;
    private String name;
    private List<Seller> follows;

}
