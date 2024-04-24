package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.exception.NotFoundException;
import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import bootcamp.sprint.grupo02.sprintI.service.SellerService;
import org.springframework.stereotype.Service;


import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository repository;
    private final SellerService sellerService;


    @Override
    public FollowedListResponseDTO searchBuyerFollows(int buyerId) {
        Buyer founded = repository.findById(buyerId)
                .orElseThrow(() -> new NotFoundException(String.format("No buyer founded with ID [%d]", buyerId)));

        List<UserResponseDTO> followedSellers = founded.getFollows()
                .stream()
                .map(sellerFollowed -> new UserResponseDTO(sellerFollowed.getId(), sellerFollowed.getName()))
                .toList();

        UserResponseDTO buyer = new UserResponseDTO(founded.getId(), founded.getName());

        return new FollowedListResponseDTO(buyer, followedSellers);
    }

    @Override
    public void followUser(int userId, int userIdToFollow) {
        Buyer buyer = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));
        Seller seller = sellerService.findById(userIdToFollow);

        buyer.getFollows().add(seller);
        seller.getFollowers().add(buyer);
    }
}
