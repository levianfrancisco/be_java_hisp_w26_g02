package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.MessageResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.PostListByBuyerResponseDTO;
import bootcamp.sprint.grupo02.sprintI.dto.response.PostResponseDTO;
import bootcamp.sprint.grupo02.sprintI.enums.DateOrder;
import bootcamp.sprint.grupo02.sprintI.exception.BadRequestException;
import bootcamp.sprint.grupo02.sprintI.model.Post;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import bootcamp.sprint.grupo02.sprintI.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import bootcamp.sprint.grupo02.sprintI.dto.request.PostDTO;
import bootcamp.sprint.grupo02.sprintI.dto.request.ProductDTO;
import bootcamp.sprint.grupo02.sprintI.model.Product;
import bootcamp.sprint.grupo02.sprintI.repository.ProductRepository;
import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.PostRepository;
import bootcamp.sprint.grupo02.sprintI.service.PostService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    
    private final PostRepository repository;
    private final BuyerService buyerService;
    private final ProductService productService;

    public List<PostResponseDTO> searchAllBySellers(List<Integer> sellersId, String order) {

        Comparator<PostResponseDTO> comparator = Comparator.comparing(PostResponseDTO::getDate);

        if(DateOrder.DATE_DESC.toString().equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        return  sellersId.stream()
                .map(repository::findBySellerId)
                .flatMap(List::stream)
                .map(this::convertToPostResponseDTO)
                .sorted(comparator)
                .toList();
    }

    @Override
    public List<PostResponseDTO> searchBySellersBetween(List<Integer> sellersId, String order, Long period) {
        return  this.searchAllBySellers(sellersId, order)
                .stream()
                .filter(p -> p.getDate().isAfter(LocalDate.now().minusDays(period)))
                .toList();
    }

    @Override
    public List<PostResponseDTO> getAllBySellerId(int seller, String order) {
        if(order.equals(DateOrder.DATE_ASC.toString().toLowerCase())) {
            return repository.findBySellerId(seller)
                    .stream()
                    .map(this::convertToPostResponseDTO)
                    .sorted(Comparator.comparing(PostResponseDTO::getDate))
                    .toList();
        } if(order.equals(DateOrder.DATE_DESC.toString().toLowerCase())) {
            return repository.findBySellerId(seller)
                    .stream()
                    .map(this::convertToPostResponseDTO)
                    .sorted(Comparator.comparing(PostResponseDTO::getDate).reversed())
                    .toList();
        }
        throw new BadRequestException("Invalid order");
    }

    @Override
    public List<PostResponseDTO> getBySellerIdLastTwoWeeks(int sellerId, String order) {
        return getAllBySellerId(sellerId, order)
                .stream()
                .filter(p -> p.getDate().isAfter(LocalDate.now().minusDays(14L)))
                .toList();
    }

    @Override
    public PostListByBuyerResponseDTO findPostsByBuyer(int id, String order) {
        List<Integer> sellers = buyerService.getAllSellers(id)
                .stream()
                .map(Seller::getId)
                .toList();

        return PostListByBuyerResponseDTO.builder()
                .userId(id)
                .posts(searchBySellersBetween(sellers, order, 14L))
                .build();
    }

    private PostResponseDTO convertToPostResponseDTO(Post post){
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setCategory(post.getCategory());
        postResponseDTO.setDate(post.getDate());
        postResponseDTO.setProduct(productService.convertToProductDTO(post.getProduct()));
        postResponseDTO.setPrice(post.getPrice());
        postResponseDTO.setPostId(post.getId());
        postResponseDTO.setUserId(post.getSellerId());
        return postResponseDTO;
    }


    @Override
    public MessageResponseDTO createPost(PostDTO dto) {

            Post post = parsePostDtoToModelWithOutDiscount(dto);
            repository.add(post);
            productService.addProduct(dto.getProduct());
            return new MessageResponseDTO("Ok");
    }


    /////////////////////METODOS PRIVADOS/////////////////////
    private Post parsePostDtoToModelWithOutDiscount(PostDTO dto){
        ProductDTO proDto = dto.getProduct();

        Product product = new Product(proDto.getProductId(),proDto.getProductName(),proDto.getType(),proDto.getColor(),
                proDto.getNotes(),proDto.getBrand());

        return new Post(repository.findAll().size() + 1,dto.getUserId(),dateFormater(dto.getDate()),dto.getCategory(),dto.getPrice(),
                product, 1,false);

    }
    private LocalDate dateFormater(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(str, formatter);
        return date;
    }
}
