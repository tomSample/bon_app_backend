package bon_appetit.api.controllers;

import bon_appetit.api.models.Adresse;
import bon_appetit.api.models.Restaurant;
import bon_appetit.api.models.Ville;
import bon_appetit.api.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/restaurants")
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/ville")
    public ResponseEntity<Ville> createVille(@RequestBody Ville ville) {
        Ville savedVille = restaurantService.saveVille(ville);
        return ResponseEntity.ok(savedVille);
    }

    @PostMapping("/adresse")
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) {
        Adresse savedAdresse = restaurantService.saveAdresse(adresse);
        return ResponseEntity.ok(savedAdresse);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurantWithDetails(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Integer id) {
        Restaurant restaurant = restaurantService.findById(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    public ResponseEntity<Iterable<Restaurant>> getAllRestaurants() {
        Iterable<Restaurant> restaurants = restaurantService.findAll();
        return ResponseEntity.ok(restaurants);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Integer id) {
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Restaurant>> getRestaurantsByTypeCuisine(@RequestParam Integer typeCuisineId) {
        List<Restaurant> restaurants = restaurantService.findByTypeCuisine(typeCuisineId);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/filterByVille")
    public ResponseEntity<List<Restaurant>> getRestaurantsByVille(@RequestParam String villeName) {
        List<Restaurant> restaurants = restaurantService.findByVilleName(villeName);
        return ResponseEntity.ok(restaurants);
    }
}