package bon_appetit.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bon_appetit.api.models.Typecuisine;
import bon_appetit.api.services.TypecuisineService;

@RestController
@RequestMapping("api/typecuisines")
@CrossOrigin(origins = "http://localhost:5173") // Remplacez par l'origine de votre choix
public class TypecuisineController {

    @Autowired
    private TypecuisineService typecuisineService;

    @PostMapping
    public ResponseEntity<Typecuisine> createTypecuisine(@RequestBody Typecuisine typeCuisine) {
        Typecuisine createdTypecuisine = typecuisineService.create(typeCuisine);
        return ResponseEntity.ok(createdTypecuisine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Typecuisine> getTypecuisine(@PathVariable Integer id) {
        Typecuisine typeCuisine = typecuisineService.findById(id);
        if (typeCuisine == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(typeCuisine);
    }

    @GetMapping
    public ResponseEntity<List<Typecuisine>> getAllTypecuisines() {
        List<Typecuisine> typecuisines = typecuisineService.findAll();
        return ResponseEntity.ok(typecuisines);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypecuisine(@PathVariable Integer id) {
        typecuisineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}