package cl.utem.inf.backend.domains;

import cl.utem.inf.backend.models.Utem;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Value Object que representa la geolocalización.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Schema(name = "GeoVO")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GeoVO extends Utem {

    /**
     * Latitud
     */
    @Schema(description = "Representa la latitud de la solicitud",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "-33.4661701")
    private Double latitude = 0.0;

    /**
     * Longitud
     */
    @Schema(description = "Representa la longitud de la solicitud",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "-70.6016703")
    private Double longitude = 0.0;

    /**
     *
     * @return obtiene latitud
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude asigna latitud
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return obtiene longitud
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude asigna longitud
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
