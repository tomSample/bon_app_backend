package bon_appetit.api.services;

import bon_appetit.api.models.*;
import bon_appetit.api.repositories.AdresseRepository;
import bon_appetit.api.repositories.RestaurantRepository;
import bon_appetit.api.repositories.VilleHasAdresseRepository;
import bon_appetit.api.repositories.VilleRepository;
import bon_appetit.api.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Restaurant createRestaurantWithDetails(Map<String, Object> restaurantData) {
        // Log the incoming data
        System.out.println("Received restaurant data: " + restaurantData);

        // Extract restaurant information
        Restaurant restaurant = new Restaurant();
        restaurant.setNom((String) restaurantData.get("nom"));
        restaurant.setSiret((String) restaurantData.get("siret"));

        // Log the types of the fields
        System.out.println("Type of nombreCouvert: " + restaurantData.get("nombreCouvert").getClass().getName());
        System.out.println("Type of capacite: " + restaurantData.get("capacite").getClass().getName());
        System.out.println("Type of delaiPreparationCommande: " + restaurantData.get("delaiPreparationCommande").getClass().getName());
        System.out.println("Type of isOpen: " + restaurantData.get("isOpen").getClass().getName());
        System.out.println("Type of utilisateurId: " + restaurantData.get("utilisateurId").getClass().getName());

        restaurant.setNombreCouvert((Integer) restaurantData.get("nombreCouvert"));
        restaurant.setCapacite((Integer) restaurantData.get("capacite"));
        restaurant.setTelephone((String) restaurantData.get("telephone"));
        restaurant.setDescription((String) restaurantData.get("description"));
        restaurant.setDelaiPreparationCommande((Integer) restaurantData.get("delaiPreparationCommande"));
        restaurant.setPhoto((String) restaurantData.get("photo"));
        restaurant.setIsOpen((Boolean) restaurantData.get("isOpen") ? (byte) 1 : (byte) 0);

        // Set utilisateurId
        Integer utilisateurId = (Integer) restaurantData.get("utilisateurId");
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(utilisateurId);
        if (utilisateur.isPresent()) {
            restaurant.setUtilisateur(utilisateur.get());
        } else {
            throw new IllegalArgumentException("Utilisateur not found with id: " + utilisateurId);
        }

        // Extract address and city information
        Map<String, Object> adresseData = (Map<String, Object>) restaurantData.get("adresse");
        Map<String, Object> villeData = (Map<String, Object>) restaurantData.get("ville");

        Adresse adresse = new Adresse();
        adresse.setNumero((String) adresseData.get("numero"));
        adresse.setRue((String) adresseData.get("rue"));
        adresse.setComplement((String) adresseData.get("complement"));

        Ville ville = new Ville();
        ville.setNom((String) villeData.get("nom"));
        ville.setCodePostal((String) villeData.get("codePostal"));

        // Save or find the city
        Optional<Ville> existingVille = villeRepository.findByNomAndCodePostal(ville.getNom(), ville.getCodePostal());
        if (existingVille.isPresent()) {
            ville = existingVille.get();
        } else {
            ville = villeRepository.save(ville);
        }

        // Save or find the address
        adresse.getVilles().add(ville);
        Optional<Adresse> existingAdresse = adresseRepository.findByNumeroAndRue(adresse.getNumero(), adresse.getRue());
        if (existingAdresse.isPresent()) {
            adresse = existingAdresse.get();
        } else {
            adresse = adresseRepository.save(adresse);
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