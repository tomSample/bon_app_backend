package bon_appetit.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bon_appetit.api.models.Connexion;

@Repository
public interface ConnexionRepository extends CrudRepository<Connexion, Integer> {
    Connexion findByLoginAndPassword(String login, String password);
}