package bon_appetit.api.repositories;

import bon_appetit.api.models.Adresse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdresseRepository extends CrudRepository<Adresse, Integer> {
  Optional<Adresse> findByNumeroAndRue(String numero, String rue);
  }