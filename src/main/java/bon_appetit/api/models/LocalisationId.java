package bon_appetit.api.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LocalisationId implements Serializable {

    private Integer adresse; // Correspond à l'entité Adresse
    private Integer utilisateur; // Correspond à l'entité Utilisateur

}