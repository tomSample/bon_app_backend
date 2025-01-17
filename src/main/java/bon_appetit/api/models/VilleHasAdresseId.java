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
public class VilleHasAdresseId implements Serializable {

    @Column(name = "ville_id", nullable = false)
    private Integer villeId;

    @Column(name = "adresse_id", nullable = false)
    private Integer adresseId;

    public VilleHasAdresseId() {
    }

    public VilleHasAdresseId(Integer villeId, Integer adresseId) {
        this.villeId = villeId;
        this.adresseId = adresseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VilleHasAdresseId that = (VilleHasAdresseId) o;
        return Objects.equals(villeId, that.villeId) && Objects.equals(adresseId, that.adresseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(villeId, adresseId);
    }
}