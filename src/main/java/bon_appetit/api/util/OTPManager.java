package bon_appetit.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.repositories.ConnexionRepository;

@Component
public class OTPManager {

    @Autowired
    private ConnexionRepository connexionRepository;

    // Vérifie si l'OTP est valide pour un utilisateur donné
    public boolean verifyOTP(String login, String otp) {
        Connexion connexion = connexionRepository.findByLogin(login);
        // si l'utilisateur existe et que l'OTP correspond
        if (connexion != null && otp.equals(connexion.getOtp())) {
            // on le supprime pour éviter les réutilisations
            connexion.setOtp(null);
            // on sauvegarde la connexion sans OTP (actualiser bdd)
            connexionRepository.save(connexion);
            return true;
        }
        return false;
    }
}