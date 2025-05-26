package bon_appetit.api.services;

import bon_appetit.api.models.*;
import bon_appetit.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ConnexionRepository connexionRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private LocalisationRepository localisationRepository;

    public ResponseEntity<?> createUtilisateur(UtilisateurDTO utilisateurDTO) {
        try {
            // Créer la connexion
            Connexion connexion = new Connexion();
            connexion.setLogin(utilisateurDTO.getUsername());
            connexion.setPassword(utilisateurDTO.getPassword());
            Connexion savedConnexion = connexionRepository.save(connexion);

            // Récupérer le rôle par ID
            Role role = roleRepository.findById(utilisateurDTO.getRole_id())
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            // Créer l'utilisateur
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(utilisateurDTO.getNom());
            utilisateur.setPrenom(utilisateurDTO.getPrenom());
            utilisateur.setEmail(utilisateurDTO.getEmail());
            utilisateur.setTelephone(utilisateurDTO.getTelephone());
            utilisateur.setRole(role);
            utilisateur.setConnexion(savedConnexion);
            Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);

            // Créer l'adresse
            Adresse adresse = new Adresse();
            adresse.setNumero(utilisateurDTO.getNumero());
            adresse.setRue(utilisateurDTO.getRue());
            adresse.setComplement(utilisateurDTO.getComplement());
            Adresse savedAdresse = adresseRepository.save(adresse);

            // Créer la ville
            Ville ville = new Ville();
            ville.setNom(utilisateurDTO.getVille());
            ville.setCodePostal(utilisateurDTO.getCodePostal());
            ville.getAdresses().add(savedAdresse); // Ajouter l'adresse à la ville
            Ville savedVille = villeRepository.save(ville);

            // Lier l'adresse à l'utilisateur avec le type d'adresse par défaut
            Localisation localisation = new Localisation();
            localisation.setAdresse(savedAdresse);
            localisation.setUtilisateur(savedUtilisateur);

            if ("travail".equalsIgnoreCase(utilisateurDTO.getTypeAdresse())) {
                localisation.setAdresseParDefaut((byte) 0);
                localisation.setAdresseTravail((byte) 1);
            } else {
                localisation.setAdresseParDefaut((byte) 1);
                localisation.setAdresseTravail((byte) 0);
            }
            localisationRepository.save(localisation);

            // Préparer la réponse
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedUtilisateur.getId());
            response.put("role", savedUtilisateur.getRole().getNom());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();

            // Préparer la réponse d'erreur
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    public Utilisateur findById(Integer id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Role findRoleByUtilisateurId(Integer id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        return utilisateur != null ? utilisateur.getRole() : null;
    }

    public Iterable<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public void deleteById(Integer id) {
        utilisateurRepository.deleteById(id);
    }
}
