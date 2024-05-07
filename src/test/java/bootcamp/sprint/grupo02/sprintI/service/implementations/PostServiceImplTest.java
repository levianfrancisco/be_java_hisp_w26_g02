package bootcamp.sprint.grupo02.sprintI.service.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bootcamp.sprint.grupo02.sprintI.dto.response.PostListByBuyerResponseDTO;
import bootcamp.sprint.grupo02.sprintI.enums.DateOrder;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import bootcamp.sprint.grupo02.sprintI.repository.PostRepository;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import bootcamp.sprint.grupo02.sprintI.service.ProductService;
import bootcamp.sprint.grupo02.sprintI.util.TestGeneratorUtil;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private PostRepository repository;
    @Mock
    private BuyerService buyerService;
    @Mock
    private ProductService productService;

    @InjectMocks
    private PostServiceImpl underTest;

    @Test
    void shouldReturnLastTwoWeeksPost() {
        List<Seller> sellers = TestGeneratorUtil.createSellers();
        when(buyerService.getAllSellers(1)).thenReturn(sellers);
        sellers.forEach(seller -> whenPostRepositoryBySeller(seller.getId()));
        PostListByBuyerResponseDTO result = underTest.findPostsByBuyer(1, DateOrder.DATE_ASC.name());
        assertEquals(8 , result.getPosts().size());
        result.getPosts()
            .forEach(post -> assertTrue(isWithinLastTwoWeeks(post.getDate())));
    }

    void whenPostRepositoryBySeller(int sellerId) {
        when(repository.findBySellerId(sellerId)).thenReturn(TestGeneratorUtil.createPostsBySellerId(sellerId));
    }

    public static boolean isWithinLastTwoWeeks(LocalDate localDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate twoWeeksAgo = currentDate.minus(14, ChronoUnit.DAYS);
        return !localDate.isBefore(twoWeeksAgo) && !localDate.isAfter(currentDate);
    }
}
