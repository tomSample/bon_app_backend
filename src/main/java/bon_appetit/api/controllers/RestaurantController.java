package bon_appetit.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bon_appetit.api.models.Restaurant;
import bon_appetit.api.services.RestaurantService;

@RestController
@RequestMapping("api/restaurants")
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Map<String, Object> restaurantData) {
        Restaurant createdRestaurant = restaurantService.createRestaurantWithDetails(restaurantData);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
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

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByUtilisateurId(@PathVariable Integer userId) {
        List<Restaurant> restaurants = restaurantService.findRestaurantsByUtilisateurId(userId);
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
