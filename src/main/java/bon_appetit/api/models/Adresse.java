package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "adresse")
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

    @ManyToMany
    @JoinTable(name = "ville_has_adresse",
            joinColumns = @JoinColumn(name = "adresse_id"),
            inverseJoinColumns = @JoinColumn(name = "ville_id"))
    private Set<Ville> villes = new LinkedHashSet<>();

}