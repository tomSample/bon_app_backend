package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "type_article_has_article", schema = "bdd_bon_appetit_2", indexes = {
        @Index(name = "fk_type_article_has_article_type_article1_idx", columnList = "type_article_id"),
        @Index(name = "fk_type_article_has_article_article1_idx", columnList = "article_id")
})
public class TypeArticleHasArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_article_id", nullable = false)
    private TypeArticle typeArticle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

}