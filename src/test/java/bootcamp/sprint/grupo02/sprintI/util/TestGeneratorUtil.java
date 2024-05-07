package bootcamp.sprint.grupo02.sprintI.util;

import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Seller;

import java.util.Comparator;
import java.util.List;

public class TestGeneratorUtil {

    public static Seller createSellerWithId(int id) {
        return Seller.builder()
                .id(id)
                .name(String.format("Seller %d", id))
                .build();
    }

    public static Buyer createBuyerWithId(int id) {
        return Buyer.builder()
                .id(id)
                .name(String.format("Buyer %d", id))
                .build();
    }

    public static List<Buyer> get4FollowersAsc() {
        return List.of(
                createBuyerWithId(1),
                createBuyerWithId(2),
                createBuyerWithId(3),
                createBuyerWithId(4)
        );
    }
    public static List<Buyer> get4FollowersDesc() {
        return List.of(
                createBuyerWithId(4),
                createBuyerWithId(3),
                createBuyerWithId(2),
                createBuyerWithId(1)
        );
    }
    public static List<Seller> get4FollowedAsc() {
        return List.of(
                createSellerWithId(1),
                createSellerWithId(2),
                createSellerWithId(3),
                createSellerWithId(4)
        );
    }
    public static List<Seller> get4FollowedDesc() {
        return List.of(
                createSellerWithId(4),
                createSellerWithId(3),
                createSellerWithId(2),
                createSellerWithId(1)
        );
    }

    public static Seller createSellerWithFollowers(int id) {
        return Seller.builder()
                .id(id)
                .name(String.format("Seller %d", id))
                .followers(get4FollowersAsc())
                .build();
    }

    public static Buyer createBuyerWithFollowed(int id) {
        return Buyer.builder()
                .id(id)
                .name(String.format("Buyer %d", id))
                .follows(get4FollowedAsc())
                .build();
    }

}
