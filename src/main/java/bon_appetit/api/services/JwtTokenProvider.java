package bon_appetit.api.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey;

    public JwtTokenProvider() {
        // Récupère la clé depuis la variable d'environnement
        this.secretKey = System.getenv("JWT_SECRET_KEY");
        if (this.secretKey == null || this.secretKey.isEmpty()) {
            throw new IllegalStateException("La clé JWT_SECRET_KEY n'est pas définie !");
        }
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 1 heure

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException ex) {
            throw new RuntimeException("Signature du token invalide !");
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Le token est expiré !");
        } catch (MalformedJwtException ex) {
            throw new RuntimeException("Token malformé !");
        } catch (Exception ex) {
            throw new RuntimeException("Token invalide !");
        }
    }
}