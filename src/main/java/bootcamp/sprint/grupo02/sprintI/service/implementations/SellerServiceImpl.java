package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.SellerFollowersResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.UserResponseDTO;
import bootcamp.sprint.grupo02.sprintI.exception.NotFoundException;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.SellerRepository;
import bootcamp.sprint.grupo02.sprintI.service.SellerService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository repository;

    public SellerFollowersResponseDTO calculateFollowersCount(int id) {
        return this.repository.findById(id)
                .map(this::toResponseSellerDTO)
                .orElseThrow(() -> new NotFoundException("No seller was found for ID: " + id));
    }

    private SellerFollowersResponseDTO toResponseSellerDTO(Seller seller) {
        SellerFollowersResponseDTO responseDTO = new SellerFollowersResponseDTO(
                new UserResponseDTO(seller.getId(), seller.getName()),
                seller.getFollowers().size()
        );

        return responseDTO;
    }
}
