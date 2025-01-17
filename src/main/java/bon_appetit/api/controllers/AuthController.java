package bon_appetit.api.controllers;

import bon_appetit.api.models.Role;
import bon_appetit.api.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bon_appetit.api.services.JwtTokenProvider;
import bon_appetit.api.services.UtilisateurService;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UtilisateurService utilisateurService;

    @Autowired
    public AuthController(JwtTokenProvider jwtTokenProvider, UtilisateurService utilisateurService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/generate-token")
    public String generateToken(@RequestParam Integer userId) {
        Utilisateur utilisateur = utilisateurService.findById(userId);
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId);
        }
        Role role = utilisateur.getRole();
        if (role == null) {
            throw new RuntimeException("Role non trouvé pour l'utilisateur avec l'ID: " + userId);
        }
        String token = jwtTokenProvider.generateToken(userId, role.getId());
        return token;
    }
}