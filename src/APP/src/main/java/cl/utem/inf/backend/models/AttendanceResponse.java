package cl.utem.inf.backend.models;

// Este modelo es para la respuesta esperada en el endpoint /attendance/user/{userId}/date/{date}
public class AttendanceResponse {
    private Attendance attendance;
    private String roomName;


    // Constructor vacío
    public AttendanceResponse() {
    }
    public AttendanceResponse(Attendance attendance, String roomName) {
        this.attendance = attendance;
        this.roomName = roomName;
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
