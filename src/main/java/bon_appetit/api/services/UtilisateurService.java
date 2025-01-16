package bon_appetit.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bon_appetit.api.models.Adresse;
import bon_appetit.api.models.Connexion;
import bon_appetit.api.models.Localisation;
import bon_appetit.api.models.Role;
import bon_appetit.api.models.Utilisateur;
import bon_appetit.api.models.UtilisateurDTO;
import bon_appetit.api.models.Ville;
import bon_appetit.api.repositories.AdresseRepository;
import bon_appetit.api.repositories.ConnexionRepository;
import bon_appetit.api.repositories.LocalisationRepository;
import bon_appetit.api.repositories.RoleRepository;
import bon_appetit.api.repositories.UtilisateurRepository;
import bon_appetit.api.repositories.VilleRepository;

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

    public Utilisateur createUtilisateur(UtilisateurDTO utilisateurDTO) {
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
        ville.setAdresse(savedAdresse);
        Ville savedVille = villeRepository.save(ville);

        // Lier l'adresse à l'utilisateur avec le type d'adresse par défaut
        Localisation localisation = new Localisation();
        localisation.setAdresse(savedAdresse);
        localisation.setUtilisateur(savedUtilisateur);
        localisation.setAdresseParDefaut((byte) 1);
        localisation.setAdresseTravail((byte) 0);
        localisationRepository.save(localisation);

        return savedUtilisateur;
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