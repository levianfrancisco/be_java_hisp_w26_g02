package bootcamp.sprint.grupo02.sprintI.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Post;
import bootcamp.sprint.grupo02.sprintI.model.Seller;

import java.util.ArrayList;
import java.util.Comparator;

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

    public static Buyer createBuyerWithIdAndFollows(int id) {
        List<Seller> follows = new ArrayList<Seller>();
        follows.add(createSellerWithId(2));
        follows.add(createSellerWithId(3));

        return Buyer.builder()
                .id(id)
                .name(String.format("Buyer %d", id))
                .follows(follows)
                .build();
    }

    public static FollowedListResponseDTO createOrderedFollowedListResponseDTO(boolean isAscending) {
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .userId(1)
                .userName("Buyer 1")
                .build();

        List<UserResponseDTO> userResponseDTOList = new ArrayList<UserResponseDTO>();
        userResponseDTOList.add(
                UserResponseDTO.builder()
                        .userId(2)
                        .userName("Seller 2")
                        .build()
        );

        userResponseDTOList.add(
                UserResponseDTO.builder()
                        .userId(3)
                        .userName("Seller 3")
                        .build()
        );

        if (!isAscending) {
            Comparator<UserResponseDTO> comparator = Comparator.comparing(UserResponseDTO::getUserName);
            comparator = comparator.reversed();
            userResponseDTOList.sort(comparator);
        }

        return FollowedListResponseDTO.builder()
                .user(userResponseDTO)
                .followed(userResponseDTOList)
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
        LocalDate nextWeek = today.plusWeeks(1);
        LocalDate[] dates = {today, lastWeek, twoWeeksAgo, threeWeeksAgo, nextWeek};

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
