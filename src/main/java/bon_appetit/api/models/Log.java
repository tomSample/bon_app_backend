package bon_appetit.api.models;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "logs", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_logs_utilisateur_idx", columnList = "utilisateur_id"),
        @Index(name = "fk_logs_action1_idx", columnList = "action_id")
})
public class Log {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Lob
    @Column(name = "commentaire", nullable = false)
    private String commentaire;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "action_id", nullable = false)
    private Action action;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

}