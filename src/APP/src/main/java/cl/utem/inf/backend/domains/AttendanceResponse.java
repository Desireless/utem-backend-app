package cl.utem.inf.backend.domains;

// Este modelo es para la respuesta esperada en el endpoint /attendance/user/{userId}/date/{date}
import cl.utem.inf.backend.models.Attendance;

public class AttendanceResponse {

    private Attendance attendance;
    private String roomName;

    // Constructor vacío
    public AttendanceResponse() {
    }

    public AttendanceResponse(Attendance attendance) {
        this.attendance = attendance;
        this.roomName = attendance.getRoom().getName();
    }

    // Getters y setters
    public Attendance getAttendance() {
        return attendance;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
