package bon_appetit.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bon_appetit.api.models.Connexion;
import bon_appetit.api.repositories.ConnexionRepository;

@Component
public class OTPGenerator {

    @Autowired
    private ConnexionRepository connexionRepository;

    // Génère un OTP aléatoire à 6 chiffres
        public String generateOTP() {
            // %06d formate le nombre pour qu'il ait 6 chiffres, en ajoutant des zéros devant si nécessaire
            return String.format("%06d", (int) (Math.random() * 1000000)); // Génère un nombre entre 000000 et 999999
        }

    // Met à jour l'OTP dans la base de données
    public void updateOTP(String login) {
        Connexion connexion = connexionRepository.findByLogin(login);
        if (connexion != null) {
            String otp = generateOTP();
            connexion.setOtp(otp);
            connexionRepository.save(connexion);
            // Affiche l'OTP dans la console API
            System.out.println("OTP généré :" + otp);
        } else {
            System.out.println("erreur login :" + login);
        }
    }
}
