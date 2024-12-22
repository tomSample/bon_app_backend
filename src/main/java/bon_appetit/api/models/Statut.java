package bon_appetit.api.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "statut", schema = "bdd_bon_appetit")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @OneToMany(mappedBy = "statut")
    private Set<Etat> etats = new LinkedHashSet<>();

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

    public Set<Etat> getEtats() {
        return etats;
    }

    public void setEtats(Set<Etat> etats) {
        this.etats = etats;
    }

}