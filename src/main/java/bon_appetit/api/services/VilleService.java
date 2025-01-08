package bon_appetit.api.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import bon_appetit.api.models.Utilisateur;
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

    public Iterable<Ville> findAll() {
        return villeRepository.findAll();
    }

    public void deleteById(Integer id) {
        villeRepository.deleteById(id);
    }

    // trouver une ville dont le nom commence par ...
    public List<String> findByNameStartingWith(String prefix) {
        return villeRepository.findByNameStartingWith(prefix);
    }
}