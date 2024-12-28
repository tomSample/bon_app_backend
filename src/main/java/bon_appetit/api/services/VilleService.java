package bon_appetit.api.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Ville;
import bon_appetit.api.repositories.VilleRepository;

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

    public List<Ville> findAll() {
        Iterable<Ville> iterable = villeRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        villeRepository.deleteById(id);
    }

//liste des villes dont le nom comment par...
    public List<String> findByNomStartingWith(String prefix) {
        return villeRepository.findByNomStartingWith(prefix);
    }
}