package bon_appetit.api.controllers;

import bon_appetit.api.entities.Recipe;
import bon_appetit.api.exceptions.TokenInvalidException;
import bon_appetit.api.repositories.RecipeRepository;
import bon_appetit.api.services.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes") // Préfixe commun à tous les endpoints
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Endpoint pour ajouter une recette (protégé par token)
    @PostMapping("/add")
    public String addRecipe(@RequestHeader("Authorization") String token,
                            @RequestParam String name,
                            @RequestParam String description) {
        // Valider le token et extraire les informations utilisateur (si nécessaire)
        Claims claims = validateToken(token);

        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);
        recipeRepository.save(recipe);

        return "Recette ajoutée par " + claims.getSubject() + " : " + name;
    }

    // Test endpoint
    @GetMapping("/test")
    public String testController() {
        return "Le contrôleur fonctionne !";
    }

    // Endpoint pour récupérer toutes les recettes (protégé par token)
    @GetMapping("")
    public List<Recipe> getRecipes(@RequestHeader("Authorization") String token) {
        validateToken(token); // Valider le token avant d'exécuter l'action

        return recipeRepository.findAll();
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
