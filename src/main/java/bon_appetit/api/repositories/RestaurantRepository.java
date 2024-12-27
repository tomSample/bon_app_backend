package bon_appetit.api.repositories;

import bon_appetit.api.models.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
    List<Restaurant> findByRepartitions_TypeCuisine_Id(Integer typeCuisineId);
}