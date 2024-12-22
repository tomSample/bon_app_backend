package bon_appetit.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur create(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findById(Integer id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Iterable<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public void deleteById(Integer id) {
        utilisateurRepository.deleteById(id);
    }
}