package bon_appetit.api.services;

import bon_appetit.api.models.Adresse;
import bon_appetit.api.models.Restaurant;
import bon_appetit.api.models.Ville;
import bon_appetit.api.repositories.AdresseRepository;
import bon_appetit.api.repositories.RestaurantRepository;
import bon_appetit.api.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private VilleRepository villeRepository;

    public Restaurant createRestaurantWithDetails(Restaurant restaurant) {
        // Create or find the address
        Adresse adresse = restaurant.getAdresse();
        if (adresse.getId() == null) {
            adresse = adresseRepository.save(adresse);
        } else {
            adresse = adresseRepository.findById(adresse.getId()).orElse(adresse);
        }

        // Create or find the city
        Ville ville = adresse.getVille();
        if (ville.getId() == null) {
            ville.setAdresse(adresse); // Set the address to the city
            ville = villeRepository.save(ville);
        } else {
            ville = villeRepository.findById(ville.getId()).orElse(ville);
        }

        // Associate the city with the address
        adresse.setVille(ville);
        adresse = adresseRepository.save(adresse);

        // Set the address to the restaurant
        restaurant.setAdresse(adresse);

        // Save the restaurant
        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Integer id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public Iterable<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public void deleteById(Integer id) {
        restaurantRepository.deleteById(id);
    }

    public List<Restaurant> findByTypeCuisine(Integer typeCuisineId) {
        return restaurantRepository.findByTypeCuisineHasRestaurants_TypeCuisine_Id(typeCuisineId);
    }

    public List<Restaurant> findByVilleName(String villeName) {
        return restaurantRepository.findByVilleName(villeName);
    }
}