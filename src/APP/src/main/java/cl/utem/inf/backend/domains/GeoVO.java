package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Utem;

/**
 * Value Object que representa la geolocalización.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
public class GeoVO extends Utem {

    /**
     * Latitud
     */
    private Double latitude = 0.0;

    /**
     * Longitud
     */
    private Double longitude = 0.0;

    /**
     * obtiene latitud
     * @return latitud
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * asigna latitud
     * @param longitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * obtiene longitud
     * @return longitud
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * asigna longitud
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
