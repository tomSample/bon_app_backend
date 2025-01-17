package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ville_has_adresse")
public class VilleHasAdresse {
    @EmbeddedId
    private VilleHasAdresseId id;

    @MapsId("villeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ville_id", nullable = false)
    private Ville ville;

    @MapsId("adresseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

}