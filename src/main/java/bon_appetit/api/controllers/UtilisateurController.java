package bon_appetit.api.controllers;

import bon_appetit.api.models.*;
import bon_appetit.api.services.UtilisateurService;
import bon_appetit.api.dto.AdresseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/utilisateurs")
@CrossOrigin(origins = "http://localhost:5173")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> createUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        return utilisateurService.createUtilisateur(utilisateurDTO);
    }

    @PostMapping("/{id}/adresses")
    public ResponseEntity<?> addAdresseToUtilisateur(
            @PathVariable Integer id,
            @RequestBody AdresseDTO adresseDTO) {
        try {
            Utilisateur utilisateur = utilisateurService.findById(id);
            if (utilisateur == null) {
                return ResponseEntity.notFound().build();
            }
            Adresse nouvelleAdresse = utilisateurService.addAdresseToUtilisateur(utilisateur, adresseDTO);
            return ResponseEntity.ok(nouvelleAdresse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utilisateur);
    }

    @GetMapping(value = "/role/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRoleByUtilisateurId(@PathVariable Integer id) {
        Role role = utilisateurService.findRoleByUtilisateurId(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<Iterable<Utilisateur>> getAllUtilisateurs() {
        Iterable<Utilisateur> utilisateurs = utilisateurService.findAll();
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/{id}/adresses")
    public ResponseEntity<?> getAdressesByUtilisateurId(@PathVariable Integer id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        Set<Localisation> localisations = utilisateur.getLocalisations();
        var adresses = localisations.stream()
                .map(localisation -> {
                    Adresse adresse = localisation.getAdresse();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", adresse.getId());
                    map.put("numero", adresse.getNumero());
                    map.put("rue", adresse.getRue());
                    map.put("complement", adresse.getComplement());
                    map.put("longitude", adresse.getLongitude());
                    map.put("latitude", adresse.getLatitude());
                    // On prend la première ville liée à l'adresse (si plusieurs, à adapter)
                    Ville ville = adresse.getVilles().stream().findFirst().orElse(null);
                    if (ville != null) {
                        map.put("codePostal", ville.getCodePostal());
                        map.put("ville", ville.getNom());
                    } else {
                        map.put("codePostal", "");
                        map.put("ville", "");
                    }
                    map.put("adresseParDefaut", localisation.getAdresseParDefaut());
                    map.put("adresseTravail", localisation.getAdresseTravail());
                    return map;
                })
                .toList();
        return ResponseEntity.ok(adresses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Integer id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
