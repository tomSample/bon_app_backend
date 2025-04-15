package bon_appetit.api.services;

import bon_appetit.api.models.Article;
import bon_appetit.api.models.Restaurant;
import bon_appetit.api.repositories.ArticleRepository;
import bon_appetit.api.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, RestaurantRepository restaurantRepository) {
        this.articleRepository = articleRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //Liste articles

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Transactional
    public Article createArticle(Article article, Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant non trouvé avec l'ID: " + restaurantId));

        article.setRestaurant(restaurant);
        return articleRepository.save(article);
    }

    //Recommandations

    @Transactional
    public Article addRecommendation(Integer articleId, Integer recommendedArticleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID: " + articleId));

        Article recommendedArticle = articleRepository.findById(recommendedArticleId)
                .orElseThrow(() -> new RuntimeException("Article recommandé non trouvé avec l'ID: " + recommendedArticleId));

        article.getArticlesRecommandes().add(recommendedArticle);
        return articleRepository.save(article);
    }

    public Set<Article> getRecommendationsForArticle(Integer articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID: " + articleId));

        return article.getArticlesRecommandes();
    }

}