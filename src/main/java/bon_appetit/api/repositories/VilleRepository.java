package bon_appetit.api.repositories;

import bon_appetit.api.models.Ville;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VilleRepository extends CrudRepository<Ville, Integer> {
    List<Ville> findByNomStartingWith(String prefix);
    List<Ville> findByCodePostalStartingWith(String prefix);
    Optional<Ville> findByNomAndCodePostal(String nom, String codePostal);
}