package bon_appetit.api.repositories;

import bon_appetit.api.models.TypeCuisineHasRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCuisineHasRestaurantRepository extends CrudRepository<TypeCuisineHasRestaurant, Integer> {
}