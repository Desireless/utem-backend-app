package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.models.Utem;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Value Object que representa las asistencias
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Schema(name = "AttendanceVO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttendanceVO extends Utem {

    /**
     * sala del registro de asistencia
     */
    @Schema(description = "Representa el nombre de la sala de clases",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "LAB. INFORMATICA N 1")
    private String room = null;

    /**
     * correo que registra la asistencia
     */
    @Schema(description = "Representa al usuario utem que registra la asistencia",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "jbastias@utem.cl")
    private String email = null;

    /**
     * fecha de la asistencia
     */
    @Schema(description = "Fecha de la asistencia")
    private OffsetDateTime date = null;

    /**
     * Constructor por defecto
     */
    public AttendanceVO() {
        this.room = null;
        this.email = null;
        this.date = OffsetDateTime.now(ZoneOffset.UTC);
    }

    /**
     * Constructor con parámetros
     *
     * @param attendance asistencia
     */
    public AttendanceVO(Attendance attendance) {
        this.room = attendance.getRoom().getName();
        this.email = attendance.getUser().getEmail();
        this.date = attendance.getCreatedAt().withOffsetSameLocal(ZoneOffset.UTC);
    }

    /**
     * sala del registro de asistencia
     *
     * @return sala
     */
    public String getRoom() {
        return room;
    }

    /**
     * correo que registra la asistencia
     *
     * @return correo
     */
    public String getEmail() {
        return email;
    }

    /**
     * fecha de la asistencia
     *
     * @return fecha
     */
    public OffsetDateTime getDate() {
        return date;
    }
}
