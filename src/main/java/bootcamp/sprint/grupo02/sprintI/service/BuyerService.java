package bootcamp.sprint.grupo02.sprintI.service;

import bootcamp.sprint.grupo02.sprintI.model.Seller;

import java.util.List;

public interface BuyerService {
    List<Seller> getAllSellers(int buyerId);
}
