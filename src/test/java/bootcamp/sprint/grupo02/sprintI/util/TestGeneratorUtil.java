package bootcamp.sprint.grupo02.sprintI.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Post;
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

    public static List<Seller> createSellers() {
        return List.of(createSellerWithId(1), createSellerWithId(2));
    }

    public static List<Integer> createSellersIdList() {
        return List.of(1, 2);
    }

    public static List<Post> createPostsBySellerId(int sellerId){
        LocalDate today = LocalDate.now();
        LocalDate lastWeek = today.minusWeeks(1);
        LocalDate twoWeeksAgo = today.minusWeeks(2);
        LocalDate threeWeeksAgo = today.minusWeeks(3);
        LocalDate thisWeek = today;
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate[] dates = {today, lastWeek, twoWeeksAgo, threeWeeksAgo, thisWeek, nextWeek};
    
        return Arrays.asList(dates)
        .stream()
        .map(date -> Post.builder()
            .sellerId(sellerId)
            .date(date)
            .build()
        )
        .toList();
        
    }
}
