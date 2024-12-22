package bon_appetit.api.controllers;

import bon_appetit.api.models.Restaurant;
import bon_appetit.api.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.create(restaurant);
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
}
