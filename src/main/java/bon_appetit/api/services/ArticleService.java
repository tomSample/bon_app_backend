package bon_appetit.api.services;

import bon_appetit.api.models.Article;
import bon_appetit.api.repositories.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}
