package bon_appetit.api.services;

import bon_appetit.api.models.Ville;
import bon_appetit.api.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public Ville create(Ville ville) {
        return villeRepository.save(ville);
    }

    public Ville findById(Integer id) {
        return villeRepository.findById(id).orElse(null);
    }

    public Iterable<Ville> findAll() {
        return villeRepository.findAll();
    }

    public void deleteById(Integer id) {
        villeRepository.deleteById(id);
    }

    public List<Ville> findByNomStartingWith(String prefix) {
        return villeRepository.findByNomStartingWith(prefix);
    }

    public List<Ville> findByCodePostalStartingWith(String prefix) {
        return villeRepository.findByCodePostalStartingWith(prefix);
    }
}