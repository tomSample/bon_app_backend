package bon_appetit.api.exceptions;

//exception personnalisée qui étend RuntimeException. Elle est utilisée pour signaler 
// des erreurs spécifiques liées à la validation ou à l'utilisation des tokens JWT
public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException(String message) {
        super(message);
    }
}
