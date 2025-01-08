package bon_appetit.api.controllers;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.services.ConnexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/connexions")
@CrossOrigin(origins = "http://localhost:5173")
public class ConnexionController {

    @Autowired
    private ConnexionService connexionService;

    // creer une Connexion + vérifie si le login existe déjà avant
    @PostMapping
    public ResponseEntity<Connexion> createConnexion(@RequestBody Connexion connexion) {
        if (connexionService.existsByLogin(connexion.getLogin())) {
            return ResponseEntity.status(409).build();
        }
        Connexion createdConnexion = connexionService.create(connexion);
        return ResponseEntity.ok(createdConnexion);
    }

    // trouver une connexion par son id
    @GetMapping("/{id}")
    public ResponseEntity<Connexion> getConnexion(@PathVariable Integer id) {
        Connexion connexion = connexionService.findById(id);
        if (connexion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(connexion);
    }

    // trouver toutes les Connexions
    @GetMapping
    public ResponseEntity<Iterable<Connexion>> getAllConnexions() {
        Iterable<Connexion> connexions = connexionService.findAll();
        return ResponseEntity.ok(connexions);
    }

    // supprimer une Connexion par son id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConnexion(@PathVariable Integer id) {
        connexionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}