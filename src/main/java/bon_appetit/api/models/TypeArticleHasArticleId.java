package bon_appetit.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TypeArticleHasArticleId implements Serializable {
    @Column(name = "type_article_id", nullable = false)
    private Integer typeArticle; // Correspond au champ "typeArticle" dans TypeArticleHasArticle

    @Column(name = "article_id", nullable = false)
    private Integer article; // Correspond au champ "article" dans TypeArticleHasArticle
}