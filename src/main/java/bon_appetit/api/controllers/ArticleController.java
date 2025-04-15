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
}