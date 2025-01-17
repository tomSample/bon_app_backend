package bon_appetit.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class VilleHasAdresseId implements Serializable {
    private static final long serialVersionUID = -1782348476095974705L;
    @Column(name = "ville_id", nullable = false)
    private Integer villeId;

    @Column(name = "adresse_id", nullable = false)
    private Integer adresseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VilleHasAdresseId entity = (VilleHasAdresseId) o;
        return Objects.equals(this.adresseId, entity.adresseId) &&
                Objects.equals(this.villeId, entity.villeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresseId, villeId);
    }

}