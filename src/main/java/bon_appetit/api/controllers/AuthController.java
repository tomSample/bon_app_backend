package bon_appetit.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bon_appetit.api.services.JwtTokenProvider;

// gère la génération de token

@RestController
@RequestMapping("/api")
public class AuthController {

   private final JwtTokenProvider jwtTokenProvider;

   public AuthController(JwtTokenProvider jwtTokenProvider) {
       this.jwtTokenProvider = jwtTokenProvider;
   }

   @GetMapping("/generate-token")
   public String generateToken(@RequestParam String username) {
       String token = jwtTokenProvider.generateToken(username);
       return token;
   }
}
