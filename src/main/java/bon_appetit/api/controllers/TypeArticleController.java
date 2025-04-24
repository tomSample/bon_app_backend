package bon_appetit.api.controllers;

import bon_appetit.api.models.TypeArticle;
import bon_appetit.api.repositories.TypeArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-articles")
@CrossOrigin(origins = "http://localhost:5173")
public class TypeArticleController {

    @Autowired
    private TypeArticleRepository typeArticleRepository;

    @GetMapping
    public ResponseEntity<List<TypeArticle>> getAllTypeArticles() {
        List<TypeArticle> typeArticles = typeArticleRepository.findAll();
        return ResponseEntity.ok(typeArticles);
    }
}