package bon_appetit.api.repositories;

import bon_appetit.api.models.TypeArticleHasArticle;
import bon_appetit.api.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeArticleHasArticleRepository extends JpaRepository<TypeArticleHasArticle, Integer> {

    @Query("SELECT a FROM TypeArticleHasArticle t " +
           "JOIN t.articleEntity a " +
           "WHERE t.typeArticle = :typeId AND a.restaurant.id = :restaurantId")
    List<Article> findArticlesByTypeAndRestaurant(
            @Param("typeId") Integer typeId,
            @Param("restaurantId") Integer restaurantId);
}