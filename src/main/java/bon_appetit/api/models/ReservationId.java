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
// clé primaire composite de Reservation.java
public class ReservationId implements Serializable {

    private Integer restaurant;
    private Integer utilisateur;

}