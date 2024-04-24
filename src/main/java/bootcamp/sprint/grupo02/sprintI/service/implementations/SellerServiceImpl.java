package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.exception.NotFoundException;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.SellerRepository;
import bootcamp.sprint.grupo02.sprintI.service.SellerService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    
    private final SellerRepository repository;

    @Override
    public Seller findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seller not found: " + id));
    }
}
