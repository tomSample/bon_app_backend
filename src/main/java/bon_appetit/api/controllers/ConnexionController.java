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
import bon_appetit.api.models.JwtResponse;
import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.services.ConnexionService;
import bon_appetit.api.services.UserDetailsService;
import bon_appetit.api.util.JwtTokenUtil;

@RestController
@RequestMapping("api/connexions")
@CrossOrigin(origins = "http://localhost:5173")
public class ConnexionController {

    @Autowired
    private ConnexionService connexionService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // creer une Connexion + vérifie si le login existe déjà avant
    @PostMapping
    public ResponseEntity<Connexion> createConnexion(@RequestBody Connexion connexion) {
        if (connexionService.existsByLogin(connexion.getLogin())) {
            return ResponseEntity.status(409).build();
        }
        Connexion createdConnexion = connexionService.create(connexion);
        return ResponseEntity.ok(createdConnexion);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkConnexion(@RequestBody Connexion connexion) {
        Connexion existingConnexion = connexionService.findByLogin(connexion.getLogin());
        if (existingConnexion == null) {
            return ResponseEntity.status(401).body("Login incorrect");
        }

        if (!connexion.getPassword().equals(existingConnexion.getPassword())) {
            return ResponseEntity.status(401).body("Mot de passe incorrect");
        }

        Utilisateur utilisateur = userDetailsService.loadUserByUsername(connexion.getLogin());
        final String token = jwtTokenUtil.generateToken(utilisateur);
        final String role = utilisateur.getRole().getNom();

        return ResponseEntity.ok(new JwtResponse(token, role));
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