package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Utem;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;

/**
 * Value Object que representa los errores.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorVO extends Utem {

    /**
     * Indica si existe un error
     */
    private boolean ok = false;

    /**
     * Mensaje de error
     */
    private String message = null;

    /**
     * Fecha de creación
     */
    private OffsetDateTime created = OffsetDateTime.now();

    /**
     * Constructor por defecto
     */
    public ErrorVO() {
        this.ok = false;
        this.message = "Error";
        this.created = OffsetDateTime.now();
    }

    /**
     * Constructor con mensaje
     *
     * @param message Mensaje de error
     */
    public ErrorVO(String message) {
        this.ok = false;
        this.message = message;
        this.created = OffsetDateTime.now();
    }

    /**
     * obtiene el estado
     * @return ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * obtiene el mensaje de error
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * obtiene la fecha de creación
     * @return created
     */
    public OffsetDateTime getCreated() {
        return created;
    }
}
