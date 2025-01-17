package bon_appetit.api.controllers;

import bon_appetit.api.models.Restaurant;
import bon_appetit.api.models.TypeCuisineHasRestaurant;
import bon_appetit.api.services.RestaurantService;
import bon_appetit.api.services.TypeCuisineHasRestaurantService;
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

    @Autowired
    private TypeCuisineHasRestaurantService typeCuisineHasRestaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurantWithDetails(restaurant);

        // Create TypeCuisineHasRestaurant entries
        for (TypeCuisineHasRestaurant typeCuisineHasRestaurant : restaurant.getTypeCuisineHasRestaurants()) {
            typeCuisineHasRestaurant.setRestaurant(createdRestaurant);
            typeCuisineHasRestaurantService.create(typeCuisineHasRestaurant);
        }

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
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Integer id) {
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Restaurant>> getRestaurantsByTypeCuisine(@RequestParam Integer typeCuisineId) {
        List<Restaurant> restaurants = restaurantService.findByTypeCuisine(typeCuisineId);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/filterByVille")
    public ResponseEntity<List<Restaurant>> getRestaurantsByVille(@RequestParam String villeNom) {
        List<Restaurant> restaurants = restaurantService.findByVilleName(villeNom);
        return ResponseEntity.ok(restaurants);
    }
}