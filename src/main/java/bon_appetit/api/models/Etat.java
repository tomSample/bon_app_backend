package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "etat", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_statut_has_commande_statut1_idx", columnList = "statut_id"),
        @Index(name = "fk_statut_has_commande_commande1_idx", columnList = "commande_id")
})
public class Etat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "statut_id", nullable = false)
    private Statut statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @Lob
    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "date")
    private Instant date;

}