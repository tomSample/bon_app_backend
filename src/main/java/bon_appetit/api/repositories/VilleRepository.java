package bon_appetit.api.repositories;

import bon_appetit.api.models.Ville;
import org.springframework.data.repository.CrudRepository;

public interface VilleRepository extends CrudRepository<Ville, Integer> {
}