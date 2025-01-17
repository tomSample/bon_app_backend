package bon_appetit.api.repositories;

import bon_appetit.api.models.Localisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalisationRepository extends CrudRepository<Localisation, Integer> {
}