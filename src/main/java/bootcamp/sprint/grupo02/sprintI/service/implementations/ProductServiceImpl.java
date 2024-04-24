package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.dto.response.ProductResponseDTO;
import bootcamp.sprint.grupo02.sprintI.model.Product;
import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.ProductRepository;
import bootcamp.sprint.grupo02.sprintI.service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository repository;

    public ProductResponseDTO convertToProductDTO(Product product){
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setBrand(product.getBrand());
        productResponseDTO.setType(product.getType());
        productResponseDTO.setId(product.getId());
        productResponseDTO.setNotes(product.getNotes());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setColor(product.getColor());
        return productResponseDTO;
    }

}
