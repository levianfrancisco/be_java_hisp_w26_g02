package bootcamp.sprint.grupo02.sprintI.service;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowersListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.SellerFollowersResponseDTO;

public interface SellerService {
    FollowersListResponseDTO getFollowersList(int id);
    SellerFollowersResponseDTO calculateFollowersCount(int id);
}