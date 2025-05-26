package bon_appetit.api.controllers;

import bon_appetit.api.models.Restaurant;
import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "http://localhost:3000") // Ajuster selon ton port frontend
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Définir le restaurant favori
    @PutMapping("/{userId}/restaurant-favori/{restaurantId}")
    public ResponseEntity<?> setRestaurantFavori(
            @PathVariable Integer userId,
            @PathVariable Integer restaurantId) {
        try {
            Utilisateur utilisateur = utilisateurService.setRestaurantFavori(userId, restaurantId);
            return ResponseEntity.ok(Map.of(
                    "message", "Restaurant favori défini avec succès",
                    "restaurantFavori", Map.of(
                            "id", utilisateur.getRestaurantFavoris().getId(),
                            "nom", utilisateur.getRestaurantFavoris().getNom()
                    )
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Supprimer le restaurant favori
    @DeleteMapping("/{userId}/restaurant-favori")
    public ResponseEntity<?> removeRestaurantFavori(@PathVariable Integer userId) {
        try {
            utilisateurService.removeRestaurantFavori(userId);
            return ResponseEntity.ok(Map.of("message", "Restaurant favori supprimé"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Récupérer le restaurant favori
    @GetMapping("/{userId}/restaurant-favori")
    public ResponseEntity<?> getRestaurantFavori(@PathVariable Integer userId) {
        try {
            Restaurant restaurant = utilisateurService.getRestaurantFavori(userId);
            if (restaurant != null) {
                return ResponseEntity.ok(Map.of(
                        "id", restaurant.getId(),
                        "nom", restaurant.getNom(),
                        "description", restaurant.getDescription(),
                        "photo", restaurant.getPhoto()
                ));
            }
            return ResponseEntity.ok(Map.of("message", "Aucun restaurant favori défini"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Modifier les préférences de notifications
    @PutMapping("/{userId}/notifications")
    public ResponseEntity<?> updateNotificationPreferences(
            @PathVariable Integer userId,
            @RequestBody Map<String, Boolean> preferences) {
        try {
            Utilisateur utilisateur = utilisateurService.updateNotificationPreferences(
                    userId, preferences);
            return ResponseEntity.ok(Map.of(
                    "message", "Préférences de notifications mises à jour",
                    "notifEmail", utilisateur.isNotifEmailActive(),
                    "notifPromo", utilisateur.isNotifPromoActive()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Récupérer les préférences de notifications
    @GetMapping("/{userId}/notifications")
    public ResponseEntity<?> getNotificationPreferences(@PathVariable Integer userId) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(userId);
            return ResponseEntity.ok(Map.of(
                    "notifEmail", utilisateur.isNotifEmailActive(),
                    "notifPromo", utilisateur.isNotifPromoActive(),
                    "notifPush", utilisateur.getNotifPush() == 1,
                    "notifCommandeEnCours", utilisateur.getNotifCommandeEnCours() == 1
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}