package bon_appetit.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Localisation;
import bon_appetit.api.models.LocalisationId;
import bon_appetit.api.repositories.LocalisationRepository;

@Service
public class LocalisationService {

    @Autowired
    private LocalisationRepository localisationRepository;

    // Créer une nouvelle localisation
    public Localisation createLocalisation(Localisation localisation) {
        localisation.setAdresseParDefaut((byte) 1); // Valeur par défaut
        localisation.setAdresseTravail((byte) 0);   // Valeur par défaut
        return localisationRepository.save(localisation);
    }

    // Trouver une localisation par clé composite
    public Optional<Localisation> findLocalisationById(LocalisationId localisationId) {
        return localisationRepository.findById(localisationId);
    }

    // Supprimer une localisation
    public void deleteLocalisation(LocalisationId localisationId) {
        localisationRepository.deleteById(localisationId);
    }
}