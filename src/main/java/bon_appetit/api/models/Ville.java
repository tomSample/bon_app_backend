package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ville")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "code_postal", nullable = false, length = 45)
    private String codePostal;

    @OneToMany(mappedBy = "ville")
    private Set<RestaurantHasVille> restaurantHasVilles = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "villes")
    private Set<Adresse> adresses = new LinkedHashSet<>();

}