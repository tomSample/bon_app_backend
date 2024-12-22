package bon_appetit.api.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "reservation", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_restaurant_has_utilisateur_restaurant1_idx", columnList = "restaurant_id"),
        @Index(name = "fk_restaurant_has_utilisateur_utilisateur1_idx", columnList = "utilisateur_id")
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(name = "dateHeure", nullable = false)
    private Instant dateHeure;

    @Column(name = "nbPersonne", nullable = false)
    private Integer nbPersonne;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Instant getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Integer getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(Integer nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

}