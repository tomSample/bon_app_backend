package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "article", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_article_restaurant1_idx", columnList = "restaurant_id")
})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "nom", nullable = false, length = 45)
    private String nom;

    @Column(name = "poids", nullable = false)
    private Integer poids;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "duree")
    private Integer duree;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "article")
    private Set<Contenu> contenus = new LinkedHashSet<>();

    @OneToMany(mappedBy = "article")
    private Set<TypeArticleHasArticle> typeArticleHasArticles = new LinkedHashSet<>();

}