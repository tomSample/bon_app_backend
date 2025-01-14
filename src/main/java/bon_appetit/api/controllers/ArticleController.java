package bon_appetit.api.controllers;

import bon_appetit.api.models.Article;
import bon_appetit.api.exceptions.TokenInvalidException;
import bon_appetit.api.models.Restaurant;
import bon_appetit.api.repositories.ArticleRepository;
import bon_appetit.api.repositories.RestaurantRepository;
import bon_appetit.api.services.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping("/api/articles") // Préfixe commun à tous les endpoints
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RestaurantRepository restaurantRepository; // Injection de RestaurantRepository

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Endpoint pour ajouter un article (protégé par token)
    @PostMapping("/add")
    public String addArticle(@RequestHeader("Authorization") String token,
                             @RequestParam String nom,
                             @RequestParam String description,
                             @RequestParam Double prix,
                             @RequestParam Integer poids,
                             @RequestParam Integer stock,
                             @RequestParam(required = false) String image,
                             @RequestParam(required = false) Integer duree,
                             @RequestParam Integer restaurantId) {
        // Valider le token et extraire les informations utilisateur (si nécessaire)
        Claims claims = validateToken(token);

        // Récupérer le restaurant depuis la base de données
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant non trouvé"));

        Article article = new Article();
        article.setNom(nom);
        article.setDescription(description);
        article.setPrix(prix);
        article.setPoids(poids);
        article.setStock(stock);
        article.setRestaurant(restaurant);

        article.setImage(image != null ? image : "default_image.png");
        article.setDuree(duree != null ? duree : 0);

        articleRepository.save(article);

        return "Article ajouté par " + claims.getSubject() + " : " + nom;
    }

    // Test endpoint
    @GetMapping("/test")
    public String testController() {
        return "Le contrôleur fonctionne !";
    }

    // Endpoint pour récupérer tous les articles (protégé par token)
    @GetMapping((""))
    public List<Article> getArticles(@RequestHeader("Authorization") String token) {
        validateToken(token); // Valider le token avant d'exécuter l'action

        return articleRepository.findAll();
    }

    // GET - Récupérer un article par ID
    @GetMapping("/{id}")
    public Article getArticleById(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        validateToken(token);
        return articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article non trouvé"));
    }

    // PUT - Modifier complètement un article
    @PutMapping("/{id}")
    public Article updateArticle(@RequestHeader("Authorization") String token,
                                 @PathVariable Integer id,
                                 @RequestBody Article updatedArticle) {
        validateToken(token);
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article non trouvé"));
        article.setNom(updatedArticle.getNom());
        article.setDescription(updatedArticle.getDescription());
        article.setPrix(updatedArticle.getPrix());
        article.setPoids(updatedArticle.getPoids());
        article.setStock(updatedArticle.getStock());
        article.setImage(updatedArticle.getImage());
        article.setDuree(updatedArticle.getDuree());
        return articleRepository.save(article);
    }

    // PATCH - Modifier partiellement un article
    @PatchMapping("/{id}")
    public Article patchArticle(@RequestHeader("Authorization") String token,
                                @PathVariable Integer id,
                                @RequestBody Article partialUpdate) {
        validateToken(token);
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article non trouvé"));
        if (partialUpdate.getNom() != null) {
            article.setNom(partialUpdate.getNom());
        }
        if (partialUpdate.getDescription() != null) {
            article.setDescription(partialUpdate.getDescription());
        }
        if (partialUpdate.getPrix() != null) {
            article.setPrix(partialUpdate.getPrix());
        }
        if (partialUpdate.getPoids() != null) {
            article.setPoids(partialUpdate.getPoids());
        }
        if (partialUpdate.getStock() != null) {
            article.setStock(partialUpdate.getStock());
        }
        if (partialUpdate.getImage() != null) {
            article.setImage(partialUpdate.getImage());
        }
        if (partialUpdate.getDuree() != null) {
            article.setDuree(partialUpdate.getDuree());
        }
        return articleRepository.save(article);
    }

    // DELETE - Supprimer un article par ID
    @DeleteMapping("/{id}")
    public String deleteArticle(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        validateToken(token);
        articleRepository.deleteById(id);
        return "Article supprimé avec succès";
    }

    private Claims validateToken(String token) {
        if (!token.startsWith("Bearer ")) {
            throw new TokenInvalidException("Le token doit commencer par 'Bearer ' !");
        }
        try {
            return jwtTokenProvider.validateToken(token.substring(7));
        } catch (Exception ex) {
            throw new TokenInvalidException("Token invalide ou expiré !");
        }
    }

    // Méthode pour valider le token et extraire les informations utilisateur
    private Claims validateAndExtractClaims(String authorizationHeader) {
        // Vérifie si le header contient "Bearer "
        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Le token doit commencer par 'Bearer ' !");
        }

        // Supprime "Bearer " pour récupérer le token brut
        String token = authorizationHeader.substring(7);

        // Valide le token et retourne les claims (payload du token)
        return jwtTokenProvider.validateToken(token);
    }
}
