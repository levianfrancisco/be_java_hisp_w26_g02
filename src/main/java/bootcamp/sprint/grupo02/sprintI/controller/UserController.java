package bootcamp.sprint.grupo02.sprintI.controller;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowersListResponseDTO;
import org.springframework.http.HttpStatus;
import bootcamp.sprint.grupo02.sprintI.dto.response.SellerFollowersResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.sprint.grupo02.sprintI.dto.response.FollowedListResponseDTO;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import bootcamp.sprint.grupo02.sprintI.service.SellerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final BuyerService buyerService;
    private final SellerService sellerService;


    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowersListResponseDTO> getFollowersList(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(
          sellerService.getFollowersList(userId)
        );
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<SellerFollowersResponseDTO> getSellerWithNumberOfFollowers(@PathVariable int userId){
        return ResponseEntity.ok(this.sellerService.calculateFollowersCount(userId));
    }


    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowedListResponseDTO> getSellersFollowed(@PathVariable int userId) {
        return ResponseEntity.ok(buyerService.searchBuyerFollows(userId));
    }

}
