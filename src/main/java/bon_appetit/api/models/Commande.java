package bon_appetit.api.models;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "commande", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_commande_utilisateur1_idx", columnList = "utilisateur_id")
})
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "poids", nullable = false)
    private Integer poids;

    @Column(name = "sacs", nullable = false)
    private Integer sacs;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "montantTtc", nullable = false)
    private Integer montantTtc;

    @Lob
    @Column(name = "instructionLivraison", nullable = false)
    private String instructionLivraison;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "commande")
    private Set<Avi> avis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Contenu> contenus = new LinkedHashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Etat> etats = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public Integer getSacs() {
        return sacs;
    }

    public void setSacs(Integer sacs) {
        this.sacs = sacs;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getMontantTtc() {
        return montantTtc;
    }

    public void setMontantTtc(Integer montantTtc) {
        this.montantTtc = montantTtc;
    }

    public String getInstructionLivraison() {
        return instructionLivraison;
    }

    public void setInstructionLivraison(String instructionLivraison) {
        this.instructionLivraison = instructionLivraison;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Set<Avi> getAvis() {
        return avis;
    }

    public void setAvis(Set<Avi> avis) {
        this.avis = avis;
    }

    public Set<Contenu> getContenus() {
        return contenus;
    }

    public void setContenus(Set<Contenu> contenus) {
        this.contenus = contenus;
    }

    public Set<Etat> getEtats() {
        return etats;
    }

    public void setEtats(Set<Etat> etats) {
        this.etats = etats;
    }

}