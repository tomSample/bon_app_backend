package bon_appetit.api.services;

import bon_appetit.api.models.Restaurant;
import bon_appetit.api.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant create(Restaurant restaurant) {
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
}