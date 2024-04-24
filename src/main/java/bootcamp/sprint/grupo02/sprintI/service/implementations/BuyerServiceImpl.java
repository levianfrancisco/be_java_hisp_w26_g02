package bootcamp.sprint.grupo02.sprintI.service.implementations;

import org.springframework.stereotype.Service;

import bootcamp.sprint.grupo02.sprintI.repository.BuyerRepository;
import bootcamp.sprint.grupo02.sprintI.service.BuyerService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    
    private final BuyerRepository repository;

}
