package bon_appetit.api.controllers;

import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/utilisateurs")
@CrossOrigin(origins = "http://localhost:5173")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.create(utilisateur);
        return ResponseEntity.ok(createdUtilisateur);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utilisateur);
    }

    @GetMapping
    public ResponseEntity<Iterable<Utilisateur>> getAllUtilisateurs() {
        Iterable<Utilisateur> utilisateurs = utilisateurService.findAll();
        return ResponseEntity.ok(utilisateurs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}