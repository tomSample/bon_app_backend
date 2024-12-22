package bon_appetit.api.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "etat", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_statut_has_commande_statut1_idx", columnList = "statut_id"),
        @Index(name = "fk_statut_has_commande_commande1_idx", columnList = "commande_id")
})
public class Etat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "statut_id", nullable = false)
    private Statut statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @Lob
    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "date")
    private Instant date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

}