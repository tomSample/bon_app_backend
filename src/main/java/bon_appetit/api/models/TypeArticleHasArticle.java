package bon_appetit.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "type_article_has_article")
@IdClass(TypeArticleHasArticleId.class) // Utilisation de la clé composite
public class TypeArticleHasArticle {

    @Id
    @Column(name = "type_article_id", nullable = false)
    private Integer typeArticle; // ID du type d'article

    @Id
    @Column(name = "article_id", nullable = false)
    private Integer article; // ID de l'article

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_article_id", nullable = false, insertable = false, updatable = false)
    private TypeArticle typeArticleEntity; // Relation avec l'entité TypeArticle

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false, insertable = false, updatable = false)
    private Article articleEntity; // Relation avec l'entité Article
}