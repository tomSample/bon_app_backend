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

import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.services.UtilisateurService;

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

    @GetMapping("/{id}/role")
    public ResponseEntity<String> getRoleNameByUtilisateurId(@PathVariable Integer id) {
        String roleName = utilisateurService.findRoleNameByUtilisateurId(id);
        if (roleName == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleName);
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