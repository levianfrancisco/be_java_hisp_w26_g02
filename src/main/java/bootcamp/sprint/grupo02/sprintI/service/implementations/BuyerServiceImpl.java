package bootcamp.sprint.grupo02.sprintI.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.exception.NotFoundException;
import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository repository;

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

}
