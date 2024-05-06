package bootcamp.sprint.grupo02.sprintI.util;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Seller;

import java.util.ArrayList;
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

    public static FollowedListResponseDTO createOrderedFollowedListResponseDTO(boolean isAscendent) {
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

        if (!isAscendent) {
            Comparator<UserResponseDTO> comparator = Comparator.comparing(UserResponseDTO::getUserName);
            comparator = comparator.reversed();
            userResponseDTOList.sort(comparator);
        }

        return FollowedListResponseDTO.builder()
                .user(userResponseDTO)
                .followed(userResponseDTOList)
                .build();
    }
}
