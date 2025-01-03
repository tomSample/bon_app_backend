package bon_appetit.api.controllers;

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

import bon_appetit.api.models.Connexion;
import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.services.ConnexionService;

@RestController
@RequestMapping("api/connexions")
@CrossOrigin(origins = "http://localhost:5173")
public class ConnexionController {

    @Autowired
    private ConnexionService connexionService;

    @PostMapping
    public ResponseEntity<Connexion> createConnexion(@RequestBody Connexion connexion) {
        Connexion createdConnexion = connexionService.create(connexion);
        return ResponseEntity.ok(createdConnexion);
    }

    @PostMapping("/login")
    public ResponseEntity<Utilisateur> login(@RequestBody Connexion connexion) {
        Utilisateur utilisateur = connexionService.verifyLogin(connexion.getLogin(), connexion.getPassword());
        if (utilisateur == null) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(utilisateur);
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