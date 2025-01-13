package bon_appetit.api.repositories;

import bon_appetit.api.models.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends CrudRepository<Action, Integer> {
    Action findByNom(String nom);
}