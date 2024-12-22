package bon_appetit.api.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "adresse", schema = "bdd_bon_appetit")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Set<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(Set<Localisation> localisations) {
        this.localisations = localisations;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Set<Ville> getVilles() {
        return villes;
    }

    public void setVilles(Set<Ville> villes) {
        this.villes = villes;
    }

}