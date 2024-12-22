package bon_appetit.api.controllers;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.services.ConnexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/connexions")
public class ConnexionController {

    @Autowired
    private ConnexionService connexionService;

    @PostMapping
    public ResponseEntity<Connexion> createConnexion(@RequestBody Connexion connexion) {
        Connexion createdConnexion = connexionService.create(connexion);
        return ResponseEntity.ok(createdConnexion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Connexion> getConnexion(@PathVariable Integer id) {
        Connexion connexion = connexionService.findById(id);
        if (connexion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(connexion);
    }

    @GetMapping
    public ResponseEntity<Iterable<Connexion>> getAllConnexions() {
        Iterable<Connexion> connexions = connexionService.findAll();
        return ResponseEntity.ok(connexions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConnexion(@PathVariable Integer id) {
        connexionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}