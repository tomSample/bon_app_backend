package bon_appetit.api.services;

import bon_appetit.api.models.VilleHasAdresse;
import bon_appetit.api.models.VilleHasAdresseId;
import bon_appetit.api.repositories.VilleHasAdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VilleHasAdresseService {

    @Autowired
    private VilleHasAdresseRepository villeHasAdresseRepository;

    public VilleHasAdresse saveVilleHasAdresse(VilleHasAdresse villeHasAdresse) {
        return villeHasAdresseRepository.save(villeHasAdresse);
    }

    public boolean existsById(VilleHasAdresseId id) {
        return villeHasAdresseRepository.existsById(id);
    }
}