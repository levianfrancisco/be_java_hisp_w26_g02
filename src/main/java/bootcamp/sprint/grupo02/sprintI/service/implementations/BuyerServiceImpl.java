package bootcamp.sprint.grupo02.sprintI.service.implementations;

import bootcamp.sprint.grupo02.sprintI.exception.NotFoundException;
import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    
    private final BuyerRepository repository;

    public List<Seller> getAllSellers(int buyerId){
        Optional<Buyer> buyer = repository.findById(buyerId);
        if(!buyer.isPresent()){
            throw new NotFoundException("No se encontró el comprador");
        }
        return buyer.get().getFollows();
    }

}
