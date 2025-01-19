package bon_appetit.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LocalisationId implements Serializable {

    @Column(name = "adresse_id", nullable = false)
    private Integer adresseId;

    @Column(name = "utilisateur_id", nullable = false)
    private Integer utilisateurId;

    // Default constructor
    public LocalisationId() {}

    // Constructor with parameters
    public LocalisationId(Integer adresseId, Integer utilisateurId) {
        this.adresseId = adresseId;
        this.utilisateurId = utilisateurId;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalisationId that = (LocalisationId) o;
        return Objects.equals(adresseId, that.adresseId) &&
                Objects.equals(utilisateurId, that.utilisateurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresseId, utilisateurId);
    }
}