package bon_appetit.api.repositories;

import bon_appetit.api.models.TypeCuisine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCuisineRepository extends CrudRepository<TypeCuisine, Integer> {
}