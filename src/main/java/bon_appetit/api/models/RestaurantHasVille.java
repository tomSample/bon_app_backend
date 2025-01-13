package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "restaurant_has_ville", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_restaurant_has_ville_restaurant1_idx", columnList = "restaurant_id"),
        @Index(name = "fk_restaurant_has_ville_ville1_idx", columnList = "ville_id")
})
public class RestaurantHasVille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ville_id", nullable = false)
    private Ville ville;

}