package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "commande")
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

    @Column(name = "montant_ttc", nullable = false)
    private Float montantTtc;

    @Lob
    @Column(name = "instruction_livraison", nullable = false)
    private String instructionLivraison;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "commande")
    private Set<Avi> avis = new LinkedHashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Contenu> contenus = new LinkedHashSet<>();

    @OneToMany(mappedBy = "commande")
    private Set<Etat> etats = new LinkedHashSet<>();

}