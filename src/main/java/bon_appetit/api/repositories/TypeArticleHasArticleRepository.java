package bon_appetit.api.repositories;

import bon_appetit.api.models.TypeArticleHasArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeArticleHasArticleRepository extends JpaRepository<TypeArticleHasArticle, Integer> {
}