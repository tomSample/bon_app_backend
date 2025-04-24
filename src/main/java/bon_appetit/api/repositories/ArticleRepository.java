package bon_appetit.api.repositories;

import bon_appetit.api.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByRestaurantId(Integer restaurantId);
}