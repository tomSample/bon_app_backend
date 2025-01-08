package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "adresse", schema = "bdd_bon_appetit_2")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "numero", nullable = false, length = 45)
    private String numero;

    @Column(name = "rue", nullable = false, length = 45)
    private String rue;

    @Column(name = "complement", length = 45)
    private String complement;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @OneToMany(mappedBy = "adresse")
    private Set<Localisation> localisations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "adresse")
    private Set<Restaurant> restaurants = new LinkedHashSet<>();

    @OneToMany(mappedBy = "adresse")
    private Set<Ville> villes = new LinkedHashSet<>();

}