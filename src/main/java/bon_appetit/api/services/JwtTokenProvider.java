package bon_appetit.api.services;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenProvider {

    private final Key key;

    public JwtTokenProvider() {
        // Récupère la clé depuis la variable d'environnement
        String secretKey = System.getenv("JWT_SECRET_KEY");
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("La clé JWT_SECRET_KEY n'est pas définie !");
        }
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Integer userId, Integer roleId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 1 heure

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("roleId", roleId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
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