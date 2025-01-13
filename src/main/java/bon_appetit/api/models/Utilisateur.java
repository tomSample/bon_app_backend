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
@Table(name = "utilisateur", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_utilisateur_role1_idx", columnList = "role_id"),
        @Index(name = "fk_utilisateur_connexion1_idx", columnList = "connexion_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "unique_utilisateur_connexion", columnNames = {"id", "connexion_id"})
})
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 45)
    private String prenom;

    @Column(name = "lattitude")
    private Double lattitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "telephone", nullable = false, length = 45)
    private String telephone;

    @Column(name = "notif_commande_en_cours")
    private Byte notifCommandeEnCours;

    @Column(name = "notif_email")
    private Byte notifEmail;

    @Column(name = "notif_push")
    private Byte notifPush;

    @Column(name = "notif_promo")
    private Byte notifPromo;

    @Column(name = "vehicule_livreur", length = 45)
    private String vehiculeLivreur;

    @Column(name = "restaurant_favoris", length = 45)
    private String restaurantFavoris;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "connexion_id", nullable = false)
    private Connexion connexion;

    @OneToMany(mappedBy = "utilisateur")
    private Set<Avi> avis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Commande> commandes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Localisation> localisations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Log> logs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Reservation> reservations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    private Set<Restaurant> restaurants = new LinkedHashSet<>();

}