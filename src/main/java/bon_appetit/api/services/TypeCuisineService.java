package bon_appetit.api.services;

import bon_appetit.api.models.TypeCuisine;
import bon_appetit.api.repositories.TypeCuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCuisineService {

    @Autowired
    private TypeCuisineRepository typeCuisineRepository;

    public TypeCuisine create(TypeCuisine typeCuisine) {
        return typeCuisineRepository.save(typeCuisine);
    }

    public TypeCuisine findById(Integer id) {
        return typeCuisineRepository.findById(id).orElse(null);
    }

    public Iterable<TypeCuisine> findAll() {
        return typeCuisineRepository.findAll();
    }

    public void deleteById(Integer id) {
        typeCuisineRepository.deleteById(id);
    }
}