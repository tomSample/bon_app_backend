package bon_appetit.api.controllers;

import bon_appetit.api.models.Article;
import bon_appetit.api.models.Restaurant;
import bon_appetit.api.models.TypeArticle;
import bon_appetit.api.models.TypeArticleHasArticle;
import bon_appetit.api.repositories.ArticleRepository;
import bon_appetit.api.repositories.RestaurantRepository;
import bon_appetit.api.repositories.TypeArticleHasArticleRepository;
import bon_appetit.api.repositories.TypeArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:5173")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TypeArticleRepository typeArticleRepository;

    @Autowired
    private TypeArticleHasArticleRepository typeArticleHasArticleRepository;

    // Endpoint pour créer un article
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Map<String, Object> articleData) {
        // Récupérer le restaurant
        Integer restaurantId = (Integer) articleData.get("restaurantId");
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant non trouvé"));

        // Créer l'article
        Article article = new Article();
        article.setNom((String) articleData.get("nom"));
        article.setDescription((String) articleData.get("description"));
        article.setPrix(((Number) articleData.get("prix")).doubleValue()); // Convertir en Double
        article.setImage((String) articleData.get("image"));
        article.setPoids((Integer) articleData.get("poids"));
        article.setStock((Integer) articleData.get("stock"));
        article.setDuree((Integer) articleData.get("duree"));
        article.setRestaurant(restaurant);

        // Sauvegarder l'article
        Article savedArticle = articleRepository.save(article);

        // Récupérer le type d'article
        Integer typeArticleId = (Integer) articleData.get("typeArticleId");
        TypeArticle typeArticle = typeArticleRepository.findById(typeArticleId)
                .orElseThrow(() -> new RuntimeException("Type d'article non trouvé"));

        // Créer la liaison dans la table TypeArticleHasArticle
        TypeArticleHasArticle typeArticleHasArticle = new TypeArticleHasArticle();
        typeArticleHasArticle.setTypeArticle(typeArticle.getId()); // Assigner l'ID du type d'article
        typeArticleHasArticle.setArticle(savedArticle.getId()); // Assigner l'ID de l'article
        typeArticleHasArticle.setTypeArticleEntity(typeArticle); // Associer l'entité TypeArticle
        typeArticleHasArticle.setArticleEntity(savedArticle); // Associer l'entité Article

        typeArticleHasArticleRepository.save(typeArticleHasArticle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    // Endpoint pour récupérer les articles par type et restaurant
    @GetMapping("/restaurant/{restaurantId}/type/{typeId}")
    public ResponseEntity<List<Article>> getArticlesByRestaurantAndType(
            @PathVariable Integer restaurantId,
            @PathVariable Integer typeId) {
        List<Article> articles = typeArticleHasArticleRepository.findArticlesByTypeAndRestaurant(typeId, restaurantId);
        return ResponseEntity.ok(articles);
    }
}