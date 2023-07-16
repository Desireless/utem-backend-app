package cl.utem.inf.backend.exceptions;

/**
 *
 * @author jpbb
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("No se han encontrado registros");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
