package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.exception.NotFoundException;
import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.util.TestGeneratorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static bootcamp.sprint.grupo02.sprintI.util.TestGeneratorUtil.createOrderedFollowedListResponseDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceImplTest {

    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private BuyerServiceImpl buyerService;

    @Test
    public void searchBuyerFollowsAscendingOrderTest() {
        // Arange
        FollowedListResponseDTO expectedResult = createOrderedFollowedListResponseDTO(true);
        when(buyerRepository.findById(1))
                .thenReturn(Optional.of(TestGeneratorUtil.createBuyerWithIdAndFollows(1)));

        // Act
        FollowedListResponseDTO result = buyerService.searchBuyerFollows(1, "NAME_ASC");

        // Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void searchBuyerFollowsDescendingOrderTest() {
        // Arange
        FollowedListResponseDTO expectedResult = createOrderedFollowedListResponseDTO(false);
        when(buyerRepository.findById(1))
                .thenReturn(Optional.of(TestGeneratorUtil.createBuyerWithIdAndFollows(1)));

        // Act
        FollowedListResponseDTO result = buyerService.searchBuyerFollows(1, "NAME_DESC");

        // Assert
        assertEquals(result, expectedResult);
    }

    @Test
    public void searchBuyerFollowsNonExistentOrderTest() {
        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
           buyerService.searchBuyerFollows(1, "asdasdasd");
        });
    }
}
