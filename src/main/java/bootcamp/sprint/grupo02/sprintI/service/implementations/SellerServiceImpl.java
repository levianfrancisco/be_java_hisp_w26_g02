package bootcamp.sprint.grupo02.sprintI.service.implementations;

import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.SellerRepository;
import bootcamp.sprint.grupo02.sprintI.service.SellerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    
    private final SellerRepository repository;

}
