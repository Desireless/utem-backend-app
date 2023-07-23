package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Attendance;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Respuesta personalizada para la asistencia.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
public class AttendanceResponse {

    /**
     * Objeto Asistencia
     */
    @Schema(description = "Representa los datos de la asistencia",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Attendance attendance;

    /**
     * Nombre de la sala
     */
    @Schema(description = "Representa el nombre de la sala de clases",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "LAB. INFORMATICA N 1")
    private String roomName;

    /**
     * Constructor por defecto
     */
    public AttendanceResponse() {
    }

    /**
     * Constructor con asistencia
     *
     * @param attendance Asistencia
     */
    public AttendanceResponse(Attendance attendance) {
        this.attendance = attendance;
        this.roomName = attendance.getRoom().getName();
    }

    /**
     * obtiene asistencia
     *
     * @return attendance
     */
    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * obtiene nombre de sala
     *
     * @return nombre de la sala
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * asigna nombre de la sala
     *
     * @param roomName nombre de la sala
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
