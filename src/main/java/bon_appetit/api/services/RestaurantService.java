package bon_appetit.api.services;

import bon_appetit.api.models.Adresse;
import bon_appetit.api.models.Restaurant;
import bon_appetit.api.models.Ville;
import bon_appetit.api.models.VilleHasAdresse;
import bon_appetit.api.models.VilleHasAdresseId;
import bon_appetit.api.repositories.AdresseRepository;
import bon_appetit.api.repositories.RestaurantRepository;
import bon_appetit.api.repositories.VilleHasAdresseRepository;
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

    @Autowired
    private VilleHasAdresseRepository villeHasAdresseRepository;

    public Restaurant createRestaurantWithDetails(Restaurant restaurant) {
        // Create or find the address
        Adresse adresse = restaurant.getAdresse();
        if (adresse.getId() == null) {
            adresse = adresseRepository.save(adresse);
        } else {
            adresse = adresseRepository.findById(adresse.getId()).orElse(adresse);
        }

        // Create or find the city
        Ville ville = restaurant.getAdresse().getVilles().iterator().next();
        if (ville.getId() == null) {
            ville = villeRepository.save(ville);
        } else {
            ville = villeRepository.findById(ville.getId()).orElse(ville);
        }

        // Associate the city with the address
        VilleHasAdresseId villeHasAdresseId = new VilleHasAdresseId(ville.getId(), adresse.getId());
        if (!villeHasAdresseRepository.existsById(villeHasAdresseId)) {
            VilleHasAdresse villeHasAdresse = new VilleHasAdresse();
            villeHasAdresse.setAdresse(adresse);
            villeHasAdresse.setVille(ville);
            villeHasAdresse.setId(villeHasAdresseId);
            villeHasAdresseRepository.save(villeHasAdresse);
        }

        // Set the address to the restaurant
        restaurant.setAdresse(adresse);

        // Save the restaurant
        return restaurantRepository.save(restaurant);
    }

    public Ville saveVille(Ville ville) {
        return villeRepository.save(ville);
    }

    public Adresse saveAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
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