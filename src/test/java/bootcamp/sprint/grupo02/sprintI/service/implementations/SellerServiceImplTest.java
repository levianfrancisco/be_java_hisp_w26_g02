package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.SellerFollowersResponseDTO;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import bootcamp.sprint.grupo02.sprintI.repository.SellerRepository;
import bootcamp.sprint.grupo02.sprintI.util.TestGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceImplTest {
    @Mock
    SellerRepository sellerRepository;
    @InjectMocks
    SellerServiceImpl sellerService;

    @Test
    public void calculateFollowersCountTest() {
        Seller seller = TestGeneratorUtil.createSellerWithId(1);
        seller.setFollowers(List.of(TestGeneratorUtil.createBullerWithId(1),
                TestGeneratorUtil.createBullerWithId(2)));
        when(sellerRepository.findById(seller.getId())).thenReturn(Optional.of(seller));

        SellerFollowersResponseDTO sellerFollowersResponseDTO = this.sellerService.calculateFollowersCount(seller.getId());

        Assertions.assertEquals(2, sellerFollowersResponseDTO.getFollowersCount());
    }
}
