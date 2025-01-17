package bon_appetit.api.services;

import bon_appetit.api.models.Ville;
import bon_appetit.api.models.VilleResponse;
import bon_appetit.api.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoService {

    @Autowired
    private VilleRepository villeRepository;

    private final String API_URL = "https://geo.api.gouv.fr/communes?";

    public List<Ville> fetchAndSaveData(String cityName, String postalCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL;
        if (cityName != null && !cityName.isEmpty()) {
            url += "nom=" + cityName + "&fields=nom,codesPostaux&format=json";
        } else if (postalCode != null && !postalCode.isEmpty()) {
            url += "codePostal=" + postalCode + "&fields=nom,codesPostaux&format=json";
        }

        VilleResponse[] response = restTemplate.getForObject(url, VilleResponse[].class);
        List<Ville> villes = new ArrayList<>();

        if (response != null) {
            for (VilleResponse villeResponse : response) {
                for (String codePostal : villeResponse.getCodesPostaux()) {
                    Ville ville = new Ville();
                    ville.setNom(villeResponse.getNom());
                    ville.setCodePostal(codePostal);
                    villeRepository.save(ville);
                    villes.add(ville);
                }
            }
        }

        return villes;
    }
}