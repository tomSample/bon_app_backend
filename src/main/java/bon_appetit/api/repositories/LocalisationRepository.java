package bon_appetit.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bon_appetit.api.models.Localisation;
import bon_appetit.api.models.LocalisationId;

@Repository
public interface LocalisationRepository extends CrudRepository<Localisation, LocalisationId> {
}