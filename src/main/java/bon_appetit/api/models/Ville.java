package bon_appetit.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ville", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_ville_adresse1_idx", columnList = "adresse_id")
})
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "codePostal", nullable = false, length = 45)
    private String codePostal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

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

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

}