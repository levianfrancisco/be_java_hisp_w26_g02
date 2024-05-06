package bootcamp.sprint.grupo02.sprintI.util;

import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Seller;

public class TestGeneratorUtil {

    public static Seller createSellerWithId(int id) {
        return Seller.builder()
                .id(id)
                .name(String.format("Seller %d", id))
                .build();
    }

    public static Buyer createBullerWithId(int id) {
        return Buyer.builder()
                .id(id)
                .name(String.format("Buyer %d", id))
                .build();
    }

}
