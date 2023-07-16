package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Utem;

// Value Object
public class GeoVO extends Utem {

    private Double latitude = 0.0;
    private Double longitude = 0.0;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
