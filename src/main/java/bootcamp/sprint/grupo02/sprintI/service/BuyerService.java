package bootcamp.sprint.grupo02.sprintI.service;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;

public interface BuyerService {
    void followUser(int userId, int userIdToFollow);



    FollowedListResponseDTO searchBuyerFollows(int buyerId);

}
