package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_restaurant_utilisateur1_idx", columnList = "utilisateur_id"),
        @Index(name = "fk_restaurant_adresse1_idx", columnList = "adresse_id")
})
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "siret", nullable = false, length = 45)
    private String siret;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "nombre_couvert", nullable = false)
    private Integer nombreCouvert;

    @Column(name = "capacite", nullable = false)
    private Integer capacite;

    @Column(name = "telephone", nullable = false, length = 45)
    private String telephone;

    @Lob
    @Column(name = "photo")
    private String photo;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_open", nullable = false)
    private Boolean isOpen;

    @Column(name = "delai_preparation_commande", nullable = false)
    private Integer delaiPreparationCommande;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @OneToMany(mappedBy = "restaurant")
    private Set<Article> articles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private Set<Reservation> reservations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private Set<RestaurantHasVille> restaurantHasVilles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private Set<TypeCuisineHasRestaurant> typeCuisineHasRestaurants = new LinkedHashSet<>();

}