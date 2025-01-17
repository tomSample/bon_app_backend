package bon_appetit.api.controllers;

import bon_appetit.api.models.TypeCuisine;
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

import bon_appetit.api.services.TypeCuisineService;

@RestController
@RequestMapping("api/typecuisines")
@CrossOrigin(origins = "http://localhost:5173") // Remplacez par l'origine de votre choix
public class TypeCuisineController {

    @Autowired
    private TypeCuisineService typecuisineService;

    @PostMapping
    public ResponseEntity<TypeCuisine> createTypeCuisine(@RequestBody TypeCuisine typeCuisine) {
        TypeCuisine createdTypeCuisine = typecuisineService.create(typeCuisine);
        return ResponseEntity.ok(createdTypeCuisine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCuisine> getTypeCuisine(@PathVariable Integer id) {
        TypeCuisine typeCuisine = typecuisineService.findById(id);
        if (typeCuisine == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(typeCuisine);
    }

    @GetMapping
    public ResponseEntity<Iterable<TypeCuisine>> getAllTypeCuisine() {
        Iterable<TypeCuisine> typesCuisine = typecuisineService.findAll();
        return ResponseEntity.ok(typesCuisine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeCuisine(@PathVariable Integer id) {
        typecuisineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}