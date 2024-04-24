package bootcamp.sprint.grupo02.sprintI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import bootcamp.sprint.grupo02.sprintI.service.SellerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final BuyerService buyerService;
    private final SellerService sellerService;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> postFollowUser(@PathVariable int userId, @PathVariable int userIdToFollow) {
        buyerService.followUser(userId, userIdToFollow);
        return ResponseEntity.ok().build();
    }
}
