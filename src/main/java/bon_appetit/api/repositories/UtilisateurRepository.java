package bon_appetit.api.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.models.Utilisateur;


@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer>{
    Utilisateur findByConnexion(Connexion connexion);

    @Query("SELECT r.nom FROM Role r JOIN r.utilisateurs u WHERE u.id = :id")
    String findRoleNameByUtilisateurId(@Param("id") Integer id);
}