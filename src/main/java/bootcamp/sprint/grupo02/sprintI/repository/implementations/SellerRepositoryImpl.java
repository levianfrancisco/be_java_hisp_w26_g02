package bootcamp.sprint.grupo02.sprintI.repository.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import bootcamp.sprint.grupo02.sprintI.model.Buyer;
import bootcamp.sprint.grupo02.sprintI.model.Seller;
import bootcamp.sprint.grupo02.sprintI.repository.SellerRepository;

@Repository
public class SellerRepositoryImpl implements SellerRepository {

    private List<Seller> sellers;

    public SellerRepositoryImpl() {
        this.sellers = new ArrayList<>();
        Seller seller1 = new Seller(1, "Un Vendedor", null);
        Seller seller2 = new Seller(2, "Un Vendedor 2", null);
        Buyer buyer = new Buyer(1, "Seguidor", null);
        seller1.setFollowers(List.of(buyer));
        this.sellers.add(seller1);
        this.sellers.add(seller2);
    }

    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<Seller> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void add(Seller entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void remove(Seller entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    
}
