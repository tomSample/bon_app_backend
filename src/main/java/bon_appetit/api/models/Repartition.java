package bon_appetit.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "repartition", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_restaurant_has_typeCuisine_restaurant1_idx", columnList = "restaurant_id"),
        @Index(name = "fk_restaurant_has_typeCuisine_typeCuisine1_idx", columnList = "typeCuisine_Id")
})
public class Repartition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeCuisine_Id", nullable = false)
    private Typecuisine typeCuisine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Typecuisine getTypeCuisine() {
        return typeCuisine;
    }

    public void setTypeCuisine(Typecuisine typeCuisine) {
        this.typeCuisine = typeCuisine;
    }

}