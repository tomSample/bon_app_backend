package bon_appetit.api.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "localisation", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_adresse_has_utilisateur_adresse1_idx", columnList = "adresse_id"),
        @Index(name = "fk_adresse_has_utilisateur_utilisateur1_idx", columnList = "utilisateur_id")
})
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ColumnDefault("1")
    @Column(name = "`default`", nullable = false)
    private Byte defaultField;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Byte getDefaultField() {
        return defaultField;
    }

    public void setDefaultField(Byte defaultField) {
        this.defaultField = defaultField;
    }

}