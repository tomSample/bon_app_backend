package bon_appetit.api.models;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateur", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_utilisateur_role1_idx", columnList = "role_id"),
        @Index(name = "fk_utilisateur_connexion1_idx", columnList = "connexion_id")
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

    @Column(name = "notificationCommandeEnCours")
    private Byte notificationCommandeEnCours;

    @Column(name = "notificationEmail")
    private Byte notificationEmail;

    @Column(name = "notificationPush")
    private Byte notificationPush;

    @Column(name = "notificationPromotion")
    private Byte notificationPromotion;

    @Column(name = "vehiculeLivreur", length = 45)
    private String vehiculeLivreur;

    @Column(name = "restaurantFavoris", length = 45)
    private String restaurantFavoris;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Byte getNotificationCommandeEnCours() {
        return notificationCommandeEnCours;
    }

    public void setNotificationCommandeEnCours(Byte notificationCommandeEnCours) {
        this.notificationCommandeEnCours = notificationCommandeEnCours;
    }

    public Byte getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(Byte notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public Byte getNotificationPush() {
        return notificationPush;
    }

    public void setNotificationPush(Byte notificationPush) {
        this.notificationPush = notificationPush;
    }

    public Byte getNotificationPromotion() {
        return notificationPromotion;
    }

    public void setNotificationPromotion(Byte notificationPromotion) {
        this.notificationPromotion = notificationPromotion;
    }

    public String getVehiculeLivreur() {
        return vehiculeLivreur;
    }

    public void setVehiculeLivreur(String vehiculeLivreur) {
        this.vehiculeLivreur = vehiculeLivreur;
    }

    public String getRestaurantFavoris() {
        return restaurantFavoris;
    }

    public void setRestaurantFavoris(String restaurantFavoris) {
        this.restaurantFavoris = restaurantFavoris;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public Set<Avi> getAvis() {
        return avis;
    }

    public void setAvis(Set<Avi> avis) {
        this.avis = avis;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    public Set<Localisation> getLocalisations() {
        return localisations;
    }

    public void setLocalisations(Set<Localisation> localisations) {
        this.localisations = localisations;
    }

    public Set<Log> getLogs() {
        return logs;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}