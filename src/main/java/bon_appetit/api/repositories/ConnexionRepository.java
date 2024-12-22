package bon_appetit.api.repositories;

import bon_appetit.api.models.Connexion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnexionRepository extends CrudRepository<Connexion, Integer> {
}