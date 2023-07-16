package cl.utem.inf.backend.exceptions;

/**
 *
 * @author jpbb
 */
public class BadCredentialException extends RuntimeException {

    public BadCredentialException() {
        super("Credenciales inválidas");
    }

    public BadCredentialException(String message) {
        super(message);
    }
}
