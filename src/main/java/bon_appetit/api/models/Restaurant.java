package bon_appetit.api.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant", schema = "bdd_bon_appetit", indexes = {
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

    @Column(name = "nombreCouvert")
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

    @Column(name = "isOpen", nullable = false)
    private Byte isOpen;

    @Column(name = "delaiPreparationCommande")
    private Integer delaiPreparationCommande;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @OneToMany(mappedBy = "restaurant")
    private Set<Article> articles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private Set<Repartition> repartitions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurant")
    private Set<Reservation> reservations = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNombreCouvert() {
        return nombreCouvert;
    }

    public void setNombreCouvert(Integer nombreCouvert) {
        this.nombreCouvert = nombreCouvert;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Byte isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getDelaiPreparationCommande() {
        return delaiPreparationCommande;
    }

    public void setDelaiPreparationCommande(Integer delaiPreparationCommande) {
        this.delaiPreparationCommande = delaiPreparationCommande;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Repartition> getRepartitions() {
        return repartitions;
    }

    public void setRepartitions(Set<Repartition> repartitions) {
        this.repartitions = repartitions;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

}