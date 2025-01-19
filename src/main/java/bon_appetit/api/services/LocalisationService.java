package bon_appetit.api.services;

import bon_appetit.api.models.Localisation;
import bon_appetit.api.models.LocalisationId;
import bon_appetit.api.repositories.LocalisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalisationService {

    @Autowired
    private LocalisationRepository localisationRepository;

    public Localisation createLocalisation(Localisation localisation) {
        LocalisationId localisationId = new LocalisationId(localisation.getAdresse().getId(), localisation.getUtilisateur().getId());
        localisation.setId(localisationId);
        return localisationRepository.save(localisation);
    }
}