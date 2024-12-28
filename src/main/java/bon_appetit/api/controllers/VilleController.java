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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bon_appetit.api.models.Ville;
import bon_appetit.api.services.VilleService;

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

//liste des villes dont le nom commence par...
    @GetMapping("/search")
    public ResponseEntity<List<String>> searchVilles(@RequestParam String prefix) {
        List<String> villes = villeService.findByNomStartingWith(prefix);
        return ResponseEntity.ok(villes);
    }
}