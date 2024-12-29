package bon_appetit.api.config;

import java.util.List;

import bon_appetit.api.services.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider; // Champ pour JwtTokenProvider
    private final JwtAuthenticationFilter JwtAuthenticationFilter; // Champ pour JwtAuthenticationFilter

    // Constructeur unique pour injecter les dépendances
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, JwtAuthenticationFilter JwtAuthenticationFilter) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.JwtAuthenticationFilter = JwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173")); // Remplacez par l'origine de votre choix
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfiguration.setAllowedHeaders(List.of("*"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
                .authorizeHttpRequests(authorize -> authorize
                        // .requestMatchers("/api/**").permitAll() // Désactivé pour éviter que tout ne soit accessible par défaut
                        .requestMatchers("/api/generate-token").permitAll()
                        .requestMatchers("/api/recipes/**").authenticated() // Pour protéger ce endpoint
                        .anyRequest().authenticated()
                )

                .addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Ajouter JwtAuthenticationFilter avant UsernamePassword
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173") // Remplacez par l'origine de votre choix
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}