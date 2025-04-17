package bon_appetit.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.repositories.ConnexionRepository;
import bon_appetit.api.util.OTPGenerator;

@Service
public class ConnexionService {

    @Autowired
    private ConnexionRepository connexionRepository;

    @Autowired
    private OTPGenerator otpGenerator;

    public Connexion create(Connexion connexion) {
        if (connexion.getPassword() == null || connexion.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return connexionRepository.save(connexion);
    }

    public Connexion findById(Integer id) {
        return connexionRepository.findById(id).orElse(null);
    }

    public Connexion findByLogin(String login) {
        return connexionRepository.findByLogin(login);
    }

    public Iterable<Connexion> findAll() {
        return connexionRepository.findAll();
    }

    public void deleteById(Integer id) {
        connexionRepository.deleteById(id);
    }

    public boolean existsByLogin(String login) {
        return connexionRepository.existsByLogin(login);
    }

    public boolean authenticateAndGenerateOTP(String login, String password) {
        Connexion connexion = connexionRepository.findByLogin(login);
        if (connexion != null && connexion.getPassword().equals(password)) {
            // Si l'utilisateur existe et que le mot de passe est correct
            // générer un OTP
            otpGenerator.updateOTP(login);
            return true;
        }
        return false;
    }
}