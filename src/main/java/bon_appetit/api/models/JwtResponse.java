package bon_appetit.api.models;

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