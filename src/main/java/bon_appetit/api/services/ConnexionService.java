package bon_appetit.api.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Action;
import bon_appetit.api.models.Connexion;
import bon_appetit.api.models.Log;
import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.repositories.ActionRepository;
import bon_appetit.api.repositories.ConnexionRepository;
import bon_appetit.api.repositories.LogRepository;
import bon_appetit.api.repositories.UtilisateurRepository;

@Service
public class ConnexionService {

    @Autowired
    private ConnexionRepository connexionRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Connexion create(Connexion connexion) {
        if (connexion.getPassword() == null || connexion.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return connexionRepository.save(connexion);
    }

    public Connexion findById(Integer id) {
        return connexionRepository.findById(id).orElse(null);
    }

    public Iterable<Connexion> findAll() {
        return connexionRepository.findAll();
    }

    public void deleteById(Integer id) {
        connexionRepository.deleteById(id);
    }

    public Connexion findByLoginAndPassword(String login, String password) {
        return connexionRepository.findByLoginAndPassword(login, password);
    }

    public Utilisateur verifyLogin(String login, String password) {
        Connexion connexion = connexionRepository.findByLoginAndPassword(login, password);
        if (connexion != null) {
            Utilisateur utilisateur = utilisateurRepository.findByConnexion(connexion);
            if (utilisateur != null) {
                // Enregistrer le log de connexion
                Action action = actionRepository.findByNom("connexion");
                if (action == null) {
                    throw new IllegalArgumentException("Action 'connexion' not found");
                }
                Log log = new Log();
                log.setDate(Instant.now());
                log.setCommentaire("user connexion");
                log.setUtilisateur(utilisateur);
                log.setAction(action);
                logRepository.save(log);
                return utilisateur;
            }
        }
        return null;
    }
}