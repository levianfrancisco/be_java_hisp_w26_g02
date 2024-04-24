package bootcamp.sprint.grupo02.sprintI.service;

import bootcamp.sprint.grupo02.sprintI.dto.response.PostListByBuyerResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.PostResponseDTO;

import java.util.List;

public interface PostService {
    List<PostResponseDTO> getAllBySellerId(int sellerId);
    List<PostResponseDTO> getBySellerIdLastTwoWeeks(int sellerId);
    PostListByBuyerResponseDTO findPostsByBuyer(int id);

}
