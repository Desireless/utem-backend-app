package cl.utem.inf.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author jpbb
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Utem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @return Una versión que genera una string en formato humano.
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
