package bon_appetit.api.controllers;

import bon_appetit.api.models.Article;
import bon_appetit.api.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //Liste articles

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Article> createArticle(@RequestBody Article article, @PathVariable Integer restaurantId) {
        Article createdArticle = articleService.createArticle(article, restaurantId);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    //Recommandations

    @PostMapping("/{articleId}/recommendations/{recommendedArticleId}")
    public ResponseEntity<Article> addRecommendation(
            @PathVariable Integer articleId,
            @PathVariable Integer recommendedArticleId) {
        Article article = articleService.addRecommendation(articleId, recommendedArticleId);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/{articleId}/recommendations")
    public ResponseEntity<Set<Article>> getRecommendationsForArticle(
            @PathVariable Integer articleId) {
        Set<Article> recommendations = articleService.getRecommendationsForArticle(articleId);
        return ResponseEntity.ok(recommendations);
    }

    // PATCH - Modifier le statut de sélection d'un article
    @PatchMapping("/{articleId}/selection")
    public ResponseEntity<Article> toggleSelection(@PathVariable Integer articleId, @RequestParam Boolean selection) {
        try {
            Article updatedArticle = articleService.updateSelection(articleId, selection);
            return ResponseEntity.ok(updatedArticle);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - Récupérer les articles sélectionnés d'un restaurant
    @GetMapping("/restaurant/{restaurantId}/selection")
    public ResponseEntity<List<Article>> getSelectedArticlesByRestaurant(@PathVariable Integer restaurantId) {
        try {
            List<Article> selectedArticles = articleService.getSelectedArticlesByRestaurant(restaurantId);
            return ResponseEntity.ok(selectedArticles);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}