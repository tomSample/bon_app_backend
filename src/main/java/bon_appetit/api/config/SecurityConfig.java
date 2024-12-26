package bon_appetit.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//classe pour gérer requête CORS (cross orgin ress sharing)
//permet de spécifier quels domaines peuvent accéder à ses ressources
@Configuration
public class SecurityConfig {

    // Définition de la chaîne de filtres de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactivation de la protection CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())
                // Désactivation de la configuration CORS (Cross-Origin Resource Sharing)
                .cors(cors -> cors.disable())
                // Configuration des autorisations des requêtes HTTP
                .authorizeHttpRequests(authorize -> authorize
                        // Autorisation de toutes les requêtes vers les endpoints commençant par /api/
                        .requestMatchers("/api/**").permitAll()
                        // Authentification requise pour toutes les autres requêtes
                        .anyRequest().authenticated()
                );
        // Construction et retour de la chaîne de filtres de sécurité
        return http.build();
    }

    // Configuration globale CORS pour l'application
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Ajout de la configuration CORS pour toutes les routes
                registry.addMapping("/**")
                        // Autorisation des requêtes provenant de l'origine spécifiée
                        .allowedOrigins("http://localhost:5173") // Remplacez par l'origine de votre choix
                        // Autorisation des méthodes HTTP spécifiées
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // Autorisation de tous les en-têtes
                        .allowedHeaders("*")
                        // Autorisation de l'envoi des informations d'authentification (cookies, en-têtes d'autorisation)
                        .allowCredentials(true);
            }
        };
    }
}