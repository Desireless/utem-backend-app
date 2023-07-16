package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Utem;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;

/**
 *
 * @author jpbb
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorVO extends Utem {

    private boolean ok = false;
    private String message = null;
    private OffsetDateTime created = OffsetDateTime.now();

    public ErrorVO() {
        this.ok = false;
        this.message = "Error";
        this.created = OffsetDateTime.now();
    }

    public ErrorVO(String message) {
        this.ok = false;
        this.message = message;
        this.created = OffsetDateTime.now();
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getCreated() {
        return created;
    }
}
