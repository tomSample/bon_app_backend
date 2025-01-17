package bon_appetit.api.repositories;

import bon_appetit.api.models.VilleHasAdresse;
import bon_appetit.api.models.VilleHasAdresseId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleHasAdresseRepository extends CrudRepository<VilleHasAdresse, VilleHasAdresseId> {
}