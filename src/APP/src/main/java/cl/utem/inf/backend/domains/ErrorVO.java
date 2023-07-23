package cl.utem.inf.backend.domains;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import cl.utem.inf.backend.models.Utem;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Schema(description = "Indica si existe un error",
            example = "true")
    private boolean ok = false;

    /**
     * Mensaje de error
     */
    @Schema(description = "Representa el mensaje de error",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Error al realizar la operación")
    private String message = null;

    /**
     * Fecha de creación
     */
    @Schema(description = "Representa la fecha de creación",
            example = "2020-05-20T13:12:18.825Z")
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
     *
     * @return ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * obtiene el mensaje de error
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * obtiene la fecha de creación
     *
     * @return created
     */
    public OffsetDateTime getCreated() {
        return created;
    }
}
