package bon_appetit.api.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bon_appetit.api.models.Typecuisine;

@Repository
public interface TypecuisineRepository extends CrudRepository<Typecuisine, Integer> {
}