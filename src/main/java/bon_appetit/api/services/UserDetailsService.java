package bon_appetit.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.repositories.ConnexionRepository;
import bon_appetit.api.repositories.UtilisateurRepository;

@Service
public class UserDetailsService {

    @Autowired
    private ConnexionRepository connexionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {
        Connexion connexion = connexionRepository.findByLogin(username);
        if (connexion == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec le login: " + username);
        }
        return utilisateurRepository.findByConnexion(connexion);
    }
}