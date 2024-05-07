package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.exception.BadRequestException;
import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.util.TestGeneratorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceImplTest {

    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private BuyerServiceImpl buyerService;

    @BeforeEach
    public void setup() {
        when(buyerRepository.findById(1))
                .thenReturn(Optional.of(TestGeneratorUtil.createBuyerWithIdAndFollows(1)));
    }

    @Test
    @DisplayName("Tipo de ordenamiento ascendente existente.")
    public void searchBuyerFollowsAscendingOrderTest() {
        executeOrderTest("NAME_ASC");
    }

    @Test
    @DisplayName("Tipo de ordenamiento descendente existente.")
    public void searchBuyerFollowsDescendingOrderTest() {
        executeOrderTest("NAME_DESC");
    }

    @Test
    @DisplayName("Tipo de ordenamiento inexistente.")
    public void searchBuyerFollowsNonExistentOrderTest() {
        // Act & Assert
        assertThrows(BadRequestException.class, () -> {
           buyerService.searchBuyerFollows(1, "asdasdasd");
        });
    }
    
    @Test
    void getFollowedAsc() {
        int id = 1;
        String order = "NAME_ASC";
        FollowedListResponseDTO expected = new FollowedListResponseDTO();
        expected.setUser(new UserResponseDTO(1, "Buyer 1"));
        expected.setFollowed(TestGeneratorUtil.get4FollowedAsc().stream()
                .map(follower -> new UserResponseDTO(follower.getId(), follower.getName())).toList());

        when(buyerRepository.findById(id))
                .thenReturn(Optional.ofNullable(TestGeneratorUtil.createBuyerWithFollowed(id)));

        FollowedListResponseDTO result = buyerService.searchBuyerFollows(id, order);

        assertEquals(expected, result);
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

        when(buyerRepository.findById(id))
                .thenReturn(Optional.ofNullable(TestGeneratorUtil.createBuyerWithFollowed(id)));

        FollowedListResponseDTO result = buyerService.searchBuyerFollows(id, order);

        assertEquals(expected, result);
    }

    private void executeOrderTest(String order) {
        // Act & Assert
        assertDoesNotThrow(() -> {
            buyerService.searchBuyerFollows(1, order);
        });
    }
}
