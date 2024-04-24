package bootcamp.sprint.grupo02.sprintI.service;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowersListResponseDTO;

public interface SellerService {
    FollowersListResponseDTO getFollowersList(int id);
}
