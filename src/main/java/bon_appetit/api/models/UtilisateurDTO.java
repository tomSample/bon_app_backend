package bon_appetit.api.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDTO {
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numero;
    private String rue;
    private String complement;
    private String ville;
    private String codePostal;
    private Integer role_id;
}