package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "localisation", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_adresse_has_utilisateur_adresse1_idx", columnList = "adresse_id"),
        @Index(name = "fk_adresse_has_utilisateur_utilisateur1_idx", columnList = "utilisateur_id")
})
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @ColumnDefault("1")
    @Column(name = "adresse_par_defaut", nullable = false)
    private Byte adresseParDefaut;

    @ColumnDefault("0")
    @Column(name = "adresse_travail", nullable = false)
    private Byte adresseTravail;

}