package bon_appetit.api.services;

import bon_appetit.api.models.TypeCuisineHasRestaurant;
import bon_appetit.api.repositories.TypeCuisineHasRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeCuisineHasRestaurantService {

    @Autowired
    private TypeCuisineHasRestaurantRepository typeCuisineHasRestaurantRepository;

    public TypeCuisineHasRestaurant create(TypeCuisineHasRestaurant typeCuisineHasRestaurant) {
        return typeCuisineHasRestaurantRepository.save(typeCuisineHasRestaurant);
    }
}