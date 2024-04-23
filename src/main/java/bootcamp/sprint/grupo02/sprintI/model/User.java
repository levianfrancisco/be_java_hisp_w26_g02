package bootcamp.sprint.grupo02.sprintI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private boolean isSeller; /* A confirmar*/
    private List<User> followers;
    private List<User> follows;
}
