package bon_appetit.api.repositories;

import bon_appetit.api.models.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Integer> {
}