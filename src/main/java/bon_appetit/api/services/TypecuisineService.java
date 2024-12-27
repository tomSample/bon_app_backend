package bon_appetit.api.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Typecuisine;
import bon_appetit.api.repositories.TypecuisineRepository;

@Service
public class TypecuisineService {

    @Autowired
    private TypecuisineRepository typecuisineRepository;

    public Typecuisine create(Typecuisine typeCuisine) {
        return typecuisineRepository.save(typeCuisine);
    }

    public Typecuisine findById(Integer id) {
        return typecuisineRepository.findById(id).orElse(null);
    }

    public List<Typecuisine> findAll() {
        Iterable<Typecuisine> iterable = typecuisineRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                            .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        typecuisineRepository.deleteById(id);
    }
}