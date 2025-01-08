package bon_appetit.api.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.TypeCuisine;
import bon_appetit.api.repositories.TypeCuisineRepository;

@Service
public class TypeCuisineService {

    @Autowired
    private TypeCuisineRepository typecuisineRepository;

    public TypeCuisine create(TypeCuisine typeCuisine) {
        return typecuisineRepository.save(typeCuisine);
    }

    public TypeCuisine findById(Integer id) {
        return typecuisineRepository.findById(id).orElse(null);
    }

    public Iterable<TypeCuisine> findAll() {
        return typecuisineRepository.findAll();}

    public void deleteById(Integer id) {
        typecuisineRepository.deleteById(id);
    }
}