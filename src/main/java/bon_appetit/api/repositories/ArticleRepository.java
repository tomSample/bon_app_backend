package bon_appetit.api.repositories;

import bon_appetit.api.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    // Récupérer les articles sélectionnés d'un restaurant
    List<Article> findByRestaurantIdAndSelectionTrue(Integer restaurantId);

}