package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.FollowersListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.util.TestGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceImplTest {
    @Mock
    private BuyerRepository repository;
    @InjectMocks
    private BuyerServiceImpl service;

    @Test
    void getFollowedAsc() {
        int id = 1;
        String order = "NAME_ASC";
        FollowedListResponseDTO expected = new FollowedListResponseDTO();
        expected.setUser(new UserResponseDTO(1, "Buyer 1"));
        expected.setFollowed(TestGeneratorUtil.get4FollowedAsc().stream()
                .map(follower -> new UserResponseDTO(follower.getId(), follower.getName())).toList());

        when(repository.findById(id))
                .thenReturn(Optional.ofNullable(TestGeneratorUtil.createBuyerWithFollowed(id)));

        FollowedListResponseDTO result = service.searchBuyerFollows(id, order);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void getFollowedDesc() {
        int id = 1;
        String order = "NAME_DESC";
        FollowedListResponseDTO expected = new FollowedListResponseDTO();
        expected.setUser(new UserResponseDTO(1, "Buyer 1"));
        expected.setFollowed(TestGeneratorUtil.get4FollowedDesc().stream()
                .map(follower -> new UserResponseDTO(follower.getId(), follower.getName()))
                .toList());

        when(repository.findById(id))
                .thenReturn(Optional.ofNullable(TestGeneratorUtil.createBuyerWithFollowed(id)));

        FollowedListResponseDTO result = service.searchBuyerFollows(id, order);

        Assertions.assertEquals(expected, result);
    }
}
