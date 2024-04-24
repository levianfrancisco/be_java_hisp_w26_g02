package bootcamp.sprint.grupo02.sprintI.repository.implementations;

import bootcamp.sprint.grupo02.sprintI.model.Post;
import bootcamp.sprint.grupo02.sprintI.model.Product;
import bootcamp.sprint.grupo02.sprintI.repository.PostRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private List<Post> posts;

    

    public PostRepositoryImpl() {
        this.posts = new ArrayList<>();
        Product product =  new Product(1, "Termo", "Tipo 1", "Stanley", "Verde", "Muy caro para lo que es");
        Post post = new Post(1, 1, LocalDate.now(), 1, 100.0, product , 0, false);

        posts.add(post);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Optional<Post> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Post entity) {
        posts.add(entity);
    }

    @Override
    public void remove(Post entity) {

    }
}
