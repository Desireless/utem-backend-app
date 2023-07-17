package cl.utem.inf.backend.exceptions;

/**
 * Excepción que indica que las credenciales son inválidas
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
public class BadCredentialException extends RuntimeException {

    /**
     * Constructor por defecto de BadCredentialException
     */
    public BadCredentialException() {
        super("Credenciales inválidas");
    }

    /**
     * Constructor de BadCredentialException
     *
     * @param message mensaje de error
     */
    public BadCredentialException(String message) {
        super(message);
    }
}
