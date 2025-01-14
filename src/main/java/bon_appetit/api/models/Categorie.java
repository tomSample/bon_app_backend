package bon_appetit.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categorie", schema = "bdd_bon_appetit", indexes = {
        @Index(name = "fk_article_has_typeArticle_article1_idx", columnList = "article_id"),
        @Index(name = "fk_article_has_typeArticle_typeArticle1_idx", columnList = "typeArticle_id")
})
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeArticle_id", nullable = false)
    private Typearticle typeArticle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Typearticle getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(Typearticle typeArticle) {
        this.typeArticle = typeArticle;
    }

}