package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.request.PostDTO;
import bootcamp.sprint.grupo02.sprintI.dto.request.ProductDTO;
import bootcamp.sprint.grupo02.sprintI.model.Post;
import bootcamp.sprint.grupo02.sprintI.model.Product;
import bootcamp.sprint.grupo02.sprintI.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.PostRepository;
import bootcamp.sprint.grupo02.sprintI.service.PostService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    
    private final PostRepository postRepository;
    private final ProductRepository productRepository;



    @Override
    public String createPost(PostDTO dto) {

            Post post = parsePostDtoToModelWithOutDiscount(dto);
            postRepository.add(post);
            productRepository.add(post.getProduct());
            return "todo bien";
    }


    /////////////////////METODOS PRIVADOS/////////////////////
    private Post parsePostDtoToModelWithOutDiscount(PostDTO dto){
        ProductDTO proDto = dto.getProduct();

        Product product = new Product(proDto.getProduct_id(),proDto.getProduct_name(),proDto.getType(),proDto.getColor(),
                proDto.getNotes(),proDto.getBrand());

        Post post = new Post(postRepository.findAll().size() + 1,dto.getUser_id(),dateFormater(dto.getDate()),dto.getCategory(),dto.getPrice(),
                product, 1,false);

        return post;

    }
    private LocalDate dateFormater(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(str, formatter);
        return date;
    }
}
