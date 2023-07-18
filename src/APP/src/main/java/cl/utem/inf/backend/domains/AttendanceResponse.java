package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Attendance;

/**
 * Respuesta personalizada para la asistencia.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
public class AttendanceResponse {

    /**
     * Objeto Asistencia
     */
    private Attendance attendance;

    /**
     * Nombre de la sala
     */
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
