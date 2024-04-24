package bootcamp.sprint.grupo02.sprintI.service;

import bootcamp.sprint.grupo02.sprintI.dto.response.SellerFollowersResponseDTO;

public interface SellerService {

    SellerFollowersResponseDTO calculateFollowersCount(int id);

}
