package bon_appetit.api.config;

import bts.sio.demo.config.JwtAuthenticationFilter;
import bts.sio.demo.service.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigRessource {

    private final JwtTokenProvider jwtTokenProvider; // Champ pour JwtTokenProvider
    private final JwtAuthenticationFilter JwtAuthenticationFilter; // Champ pour JwtAuthenticationFilter

    // Constructeur unique pour injecter les dépendances
    public SecurityConfigRessource(JwtTokenProvider jwtTokenProvider, JwtAuthenticationFilter JwtAuthenticationFilter) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.JwtAuthenticationFilter = JwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/generate-token").permitAll()
                        .requestMatchers("/api/recipes/**").authenticated() // Protéger ce endpoint
                        .anyRequest().authenticated()
                )

                .addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Ajouter JwtAuthenticationFilter avant UsernamePassword
                .build();
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