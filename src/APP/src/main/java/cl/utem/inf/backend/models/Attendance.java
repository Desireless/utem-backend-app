package cl.utem.inf.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un registro de asistencia
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Entity
@Table(name = "attendance")
public class Attendance extends PkEntityBase {

    /**
     * sala a la que pertenece la asistencia
     */
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Room room = null;

    /**
     * usuario que registra la asistencia
     */
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private User user = null;

    /**
     * longitud de la asistencia
     */
    @Column(name = "longitude")
    private Double longitude = 0.0;

    /**
     * latitud de la asistencia
     */
    @Column(name = "latitude")
    private Double latitude = 0.0;

    /**
     * Constructor por defecto de Attendance
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Constructor de Attendance
     *
     * @param room sala a la que pertenece la asistencia
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     *
     * @return usuario que registra la asistencia
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user usuario que registra la asistencia
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return longitud de la asistencia
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude longitud de la asistencia
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return latitud de la asistencia
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude latitud de la asistencia
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
