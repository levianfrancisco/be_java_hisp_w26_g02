package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowersListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.repository.SellerRepository;
import bootcamp.sprint.grupo02.sprintI.util.TestGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceImplTest {
    @Mock
    private SellerRepository repository;
    @InjectMocks
    private SellerServiceImpl service;

    @Test
    void getFollowersListAsc() {
        int id = 1;
        String order = "NAME_ASC";
        FollowersListResponseDTO expected = new FollowersListResponseDTO();
        expected.setUser(new UserResponseDTO(1, "Seller 1"));
        expected.setFollowers(TestGeneratorUtil.get4FollowersAsc().stream()
                        .sorted(Comparator.comparing(Buyer::getName))
                        .map(follower -> new UserResponseDTO(follower.getId(), follower.getName())).toList());

        when(repository.findById(id))
                .thenReturn(Optional.ofNullable(TestGeneratorUtil.createSellerWithFollowers(id)));

        FollowersListResponseDTO result = service.getFollowersList(id, order);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getFollowersListDesc() {
        int id = 1;
        String order = "NAME_DESC";
        FollowersListResponseDTO expected = new FollowersListResponseDTO();
        expected.setUser(new UserResponseDTO(1, "Seller 1"));
        expected.setFollowers(TestGeneratorUtil.get4FollowersDesc().stream()
                .sorted(Comparator.comparing(Buyer::getName).reversed())
                .map(follower -> new UserResponseDTO(follower.getId(), follower.getName())).toList());

        when(repository.findById(id))
                .thenReturn(Optional.ofNullable(TestGeneratorUtil.createSellerWithFollowers(id)));

        FollowersListResponseDTO result = service.getFollowersList(id, order);

        Assertions.assertEquals(expected, result);
    }
}
