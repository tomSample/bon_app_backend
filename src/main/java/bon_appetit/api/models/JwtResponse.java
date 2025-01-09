package bon_appetit.api.models;

//classe simple qui encapsule deux informations : un token JWT et le rôle de l'utilisateur. Elle est utilisée 
//pour envoyer ces informations en réponse à une demande d'authentification
public class JwtResponse {
    private final String token;
    private final String role;

    public JwtResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}