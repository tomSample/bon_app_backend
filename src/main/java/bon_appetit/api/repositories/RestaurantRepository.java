package bon_appetit.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bon_appetit.api.models.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
//liste de restaurant par type de cuisine
    List<Restaurant> findByRepartitions_TypeCuisine_Id(Integer typeCuisineId);
//liste de restaurants par ville
    @Query("SELECT r FROM Restaurant r JOIN r.adresse a JOIN a.ville v WHERE v.nom = :villeNom")
    List<Restaurant> findByVilleNom(String villeNom);
}