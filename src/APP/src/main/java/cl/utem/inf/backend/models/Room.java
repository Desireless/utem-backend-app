package cl.utem.inf.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room extends PkEntityBase {

    @Column(name = "name", nullable = false)
    private String name = null;

    @ManyToOne
    @JoinColumn(name = "campus_id", referencedColumnName = "id", nullable = false)
    private Campus campus = null;

    @Column(name = "device_sn", nullable = false, unique = true)
    private String deviceSn = null;

    // Constructor vacío (requerido por JPA)
    public Room() {
    }

    // Constructor
    public Room(String name, Campus campus) {
        this.name = name;
        this.campus = campus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }
}
