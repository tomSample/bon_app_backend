package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "type_cuisine_has_restaurant", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_type_cuisine_has_restaurant_type_cuisine1_idx", columnList = "type_cuisine_id"),
        @Index(name = "fk_type_cuisine_has_restaurant_restaurant1_idx", columnList = "restaurant_id")
})
public class TypeCuisineHasRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_cuisine_id", nullable = false)
    private TypeCuisine typeCuisine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}