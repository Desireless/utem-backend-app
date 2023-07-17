package cl.utem.inf.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa a una sala
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Entity
@Table(name = "rooms")
public class Room extends PkEntityBase {

    /**
     * Nombre de la sala
     */
    @Column(name = "name", nullable = false)
    private String name = null;

    /**
     * id del Campus al que pertenece la sala
     */
    @ManyToOne
    @JoinColumn(name = "campus_id", referencedColumnName = "id", nullable = false)
    private Campus campus = null;

    /**
     * Número de serie del dispositivo de la sala
     */
    @Column(name = "device_sn", nullable = false, unique = true)
    private String deviceSn = null;

    /**
     * Constructor por defecto de Sala
     */
    public Room() {
    }

    /**
     * Constructor de Sala
     *
     * @param name Nombre de la sala
     * @param campus id del Campus al que pertenece la sala
     */
    public Room(String name, Campus campus) {
        this.name = name;
        this.campus = campus;
    }

    /**
     *
     * @return nombre de la sala
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name nombre de la sala
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Campus al que pertenece la sala
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     *
     * @param campus id del Campus al que pertenece la sala
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     *
     * @return Número de serie del dispositivo de la sala
     */
    public String getDeviceSn() {
        return deviceSn;
    }

    /**
     *
     * @param deviceSn Número de serie del dispositivo de la sala
     */
    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }
}
