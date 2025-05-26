package bon_appetit.api.services;

import bon_appetit.api.models.Restaurant;
import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.repositories.RestaurantRepository;
import bon_appetit.api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RestaurantRepository restaurantRepository;
    private final NotificationService notificationService;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              RestaurantRepository restaurantRepository,
                              NotificationService notificationService) {
        this.utilisateurRepository = utilisateurRepository;
        this.restaurantRepository = restaurantRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public Utilisateur setRestaurantFavori(Integer userId, Integer restaurantId) {
        Utilisateur utilisateur = getUtilisateurById(userId);
        Restaurant restaurant = getRestaurantById(restaurantId);

        Restaurant ancienFavori = utilisateur.getRestaurantFavoris();
        utilisateur.setRestaurantFavoris(restaurant);

        Utilisateur saved = utilisateurRepository.save(utilisateur);

        // Envoyer notification de confirmation si email activé
        if (utilisateur.isNotifEmailActive()) {
            notificationService.envoyerNotificationRestaurantFavori(utilisateur, restaurant, ancienFavori);
        }

        return saved;
    }

    @Transactional
    public void removeRestaurantFavori(Integer userId) {
        Utilisateur utilisateur = getUtilisateurById(userId);
        Restaurant ancienFavori = utilisateur.getRestaurantFavoris();

        utilisateur.setRestaurantFavoris(null);
        utilisateurRepository.save(utilisateur);

        // Notification de suppression si email activé
        if (utilisateur.isNotifEmailActive() && ancienFavori != null) {
            notificationService.envoyerNotificationSuppressionFavori(utilisateur, ancienFavori);
        }
    }

    public Restaurant getRestaurantFavori(Integer userId) {
        Utilisateur utilisateur = getUtilisateurById(userId);
        return utilisateur.getRestaurantFavoris();
    }

    @Transactional
    public Utilisateur updateNotificationPreferences(Integer userId, Map<String, Boolean> preferences) {
        Utilisateur utilisateur = getUtilisateurById(userId);

        if (preferences.containsKey("notifEmail")) {
            utilisateur.setNotifEmail(preferences.get("notifEmail"));
        }
        if (preferences.containsKey("notifPromo")) {
            utilisateur.setNotifPromo(preferences.get("notifPromo"));
        }
        if (preferences.containsKey("notifPush")) {
            utilisateur.setNotifPush(preferences.get("notifPush") ? (byte) 1 : (byte) 0);
        }
        if (preferences.containsKey("notifCommandeEnCours")) {
            utilisateur.setNotifCommandeEnCours(preferences.get("notifCommandeEnCours") ? (byte) 1 : (byte) 0);
        }

        return utilisateurRepository.save(utilisateur);
    }

    // Méthodes utilitaires
    public Utilisateur getUtilisateurById(Integer userId) {
        return utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));
    }

    private Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant non trouvé avec l'ID: " + restaurantId));
    }

    // Méthode pour récupérer tous les utilisateurs avec restaurant favori (pour les notifications)
    public java.util.List<Utilisateur> getUtilisateursAvecRestaurantFavori(Integer restaurantId) {
        return utilisateurRepository.findByRestaurantFavorisId(restaurantId);
    }
}