package bon_appetit.api.dto;

import lombok.Data;

@Data
public class AdresseDTO {
    private String numero;
    private String rue;
    private String complement;
    private Double longitude;
    private Double latitude;
    private String codePostal;
    private String ville;
    private Boolean adresseParDefaut;
    private Boolean adresseTravail;
}