package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Attendance;
import cl.utem.inf.backend.models.Utem;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 *
 * @author jpbb
 */
@Schema(name = "AttendanceVO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AttendanceVO extends Utem {

    @Schema(description = "Representa el nombre de la sala de clases",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "LAB. INFORMATICA N 1")
    private String room = null;

    @Schema(description = "Representa al usuario utem que registra la asistencia",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "jbastias@utem.cl")
    private String email = null;

    @Schema(description = "Fecha de la asistencia")
    private OffsetDateTime date = null;

    public AttendanceVO() {
        this.room = null;
        this.email = null;
        this.date = OffsetDateTime.now(ZoneOffset.UTC);
    }

    public AttendanceVO(Attendance attendance) {
        this.room = attendance.getRoom().getName();
        this.email = attendance.getUser().getEmail();
        this.date = attendance.getCreatedAt().withOffsetSameLocal(ZoneOffset.UTC);
    }

    public String getRoom() {
        return room;
    }

    public String getEmail() {
        return email;
    }

    public OffsetDateTime getDate() {
        return date;
    }
}
