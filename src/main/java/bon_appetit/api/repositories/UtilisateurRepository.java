package bon_appetit.api.repositories;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.models.Role;
import bon_appetit.api.models.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    Utilisateur findByConnexion(Connexion connexion);

    @Query("SELECT u.role FROM Utilisateur u WHERE u.id = :id")
    Role findRoleByUtilisateurId(@Param("id") Integer id);
}