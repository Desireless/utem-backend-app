package cl.utem.inf.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entidad que representa a un campus
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Entity
@Table(name = "campus")
public class Campus extends PkEntityBase {

    /**
     * facultad a la que pertenece el campus
     */
    @Column(name = "faculty", nullable = false)
    private String faculty = null;

    /**
     * nombre del campus
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * latitud del campus
     */
    @Column(name = "latitude", nullable = false)
    private Double latitude = 0.0;

    /**
     * longitud del campus
     */
    @Column(name = "longitude", nullable = false)
    private Double longitude = 0.0;

    /**
     * Constructor por defecto de Campus
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Constructor de Campus
     *
     * @param faculty facultad a la que pertenece el campus
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    /**
     *
     * @return nombre del campus
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name nombre del campus
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return latitud del campus
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude latitud del campus
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return longitud del campus
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude longitud del campus
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
