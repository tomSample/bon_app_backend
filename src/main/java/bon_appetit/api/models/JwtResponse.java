package bon_appetit.api.models;

public class JwtResponse {
    private String token;
    private String role;
    private Integer utilisateurId;

    public JwtResponse(String token, String role, Integer utilisateurId) {
        this.token = token;
        this.role = role;
        this.utilisateurId = utilisateurId;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}