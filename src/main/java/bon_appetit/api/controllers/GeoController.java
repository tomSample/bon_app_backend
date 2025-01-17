package bon_appetit.api.controllers;

import bon_appetit.api.models.Ville;
import bon_appetit.api.services.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/geo")
public class GeoController {

    @Autowired
    private GeoService geoService;

    @GetMapping("/fetch")
    public ResponseEntity<List<Ville>> fetchData(@RequestParam(required = false) String nom, @RequestParam(required = false) String codePostal) {
        List<Ville> villes = geoService.fetchAndSaveData(nom, codePostal);
        return ResponseEntity.ok(villes);
    }
}