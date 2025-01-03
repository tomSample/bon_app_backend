package bon_appetit.api.repositories;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.models.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer>{
    Utilisateur findByConnexion(Connexion connexion);
}