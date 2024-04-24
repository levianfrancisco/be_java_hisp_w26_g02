package bootcamp.sprint.grupo02.sprintI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int user_id;
    private String date;
    private ProductDTO product;
    private int category;
    private double price;
}
