package cl.utem.inf.backend.exceptions;

/**
 * Excepción que indica que no se han encontrado registros
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructor por defecto de NotFoundException
     */
    public NotFoundException() {
        super("No se han encontrado registros");
    }

    /**
     * Constructor de NotFoundException
     *
     * @param message mensaje de error
     */
    public NotFoundException(String message) {
        super(message);
    }
}
