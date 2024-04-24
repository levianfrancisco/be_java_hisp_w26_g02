package bootcamp.sprint.grupo02.sprintI.service;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;

public interface BuyerService {
    
    FollowedListResponseDTO searchBuyerFollows(int buyerId);

}
