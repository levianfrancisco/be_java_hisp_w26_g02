package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.exception.NotFoundException;
import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import bootcamp.sprint.grupo02.sprintI.repository.SellerRepository;
import bootcamp.sprint.grupo02.sprintI.service.SellerService;
import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    
    private final BuyerRepository repository;
    private final SellerService sellerService;


    @Override
    public void followUser(int userId, int userIdToFollow) {
        Buyer buyer = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));
        Seller seller = sellerService.findById(userIdToFollow);
        
        buyer.getFollows().add(seller);
        seller.getFollowers().add(buyer);
    }
}
