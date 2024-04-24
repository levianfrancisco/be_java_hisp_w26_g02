package bootcamp.sprint.grupo02.sprintI.controller;

import bootcamp.sprint.grupo02.sprintI.dto.response.PostListByBuyerResponseDTO;
import bootcamp.sprint.grupo02.sprintI.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.sprint.grupo02.sprintI.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final PostService postService;

    @GetMapping("/followed/{userId}/list")
    public PostListByBuyerResponseDTO getPostByUser(@PathVariable int userId){
        return postService.findPostsByBuyer(userId);
    }
}
