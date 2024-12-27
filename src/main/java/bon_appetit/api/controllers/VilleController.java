package bon_appetit.api.controllers;

import bon_appetit.api.models.Ville;
import bon_appetit.api.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/villes")
@CrossOrigin(origins = "http://localhost:5173") // Remplacez par l'origine de votre choix
public class VilleController {

    @Autowired
    private VilleService villeService;

    @PostMapping
    public ResponseEntity<Ville> createVille(@RequestBody Ville ville) {
        Ville createdVille = villeService.create(ville);
        return ResponseEntity.ok(createdVille);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVille(@PathVariable Integer id) {
        Ville ville = villeService.findById(id);
        if (ville == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ville);
    }

    @GetMapping
    public ResponseEntity<Iterable<Ville>> getAllVilles() {
        Iterable<Ville> villes = villeService.findAll();
        return ResponseEntity.ok(villes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable Integer id) {
        villeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}