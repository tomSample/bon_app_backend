package bon_appetit.api.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import bon_appetit.api.models.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// Fournit des méthodes pour générer et valider des tokens JWT
@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private final String secretKey = System.getenv("JWT_SECRET_KEY");

    public String generateToken(Utilisateur utilisateur) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 1 heure

        String userId = utilisateur.getId().toString();
        String role = utilisateur.getRole().getNom();

        // Logging output
        logger.debug("Generating token for userId: {}, role: {}", userId, role);

        return Jwts.builder()
                .setSubject(utilisateur.getConnexion().getLogin())
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}