package cl.utem.inf.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance extends PkEntityBase {

    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private Room room = null;

    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private User user = null;

    @Column(name = "longitude")
    private Double longitude = 0.0;

    @Column(name = "latitude")
    private Double latitude = 0.0;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
