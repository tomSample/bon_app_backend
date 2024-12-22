package bon_appetit.api.services;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.repositories.ConnexionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnexionService {

    @Autowired
    private ConnexionRepository connexionRepository;

    public Connexion create(Connexion connexion) {
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
}