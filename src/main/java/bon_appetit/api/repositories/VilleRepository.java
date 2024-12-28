package bon_appetit.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bon_appetit.api.models.Ville;

public interface VilleRepository extends CrudRepository<Ville, Integer> {
    @Query("SELECT DISTINCT v.nom FROM Ville v WHERE v.nom LIKE :prefix%")
    List<String> findByNomStartingWith(String prefix);
}