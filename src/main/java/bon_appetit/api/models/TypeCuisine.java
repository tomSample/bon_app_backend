package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "type_cuisine", schema = "bdd_bon_appetit_2")
public class TypeCuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 45)
    private String nom;

    @OneToMany(mappedBy = "typeCuisine")
    private Set<TypeCuisineHasRestaurant> typeCuisineHasRestaurants = new LinkedHashSet<>();

}