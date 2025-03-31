package bon_appetit.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bon_appetit.api.models.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    // Liste des restaurants par utilisateur
    @Query("SELECT r FROM Restaurant r WHERE r.utilisateur.id = :utilisateurId")
    List<Restaurant> findByUtilisateurId(@Param("utilisateurId") Integer utilisateurId);

    // Liste de restaurants par type de cuisine
    List<Restaurant> findByTypeCuisineHasRestaurants_TypeCuisine_Id(Integer typeCuisineId);

    // Liste de restaurants par ville
    @Query("SELECT r FROM Restaurant r JOIN r.adresse a JOIN a.villes v WHERE v.nom = :villeNom")
    List<Restaurant> findByVilleName(String villeNom);
}