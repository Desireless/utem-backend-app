package cl.utem.inf.backend.conf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jpbb
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de registro de asistencia",
                version = "0.9.9",
                contact = @Contact(
                        name = "Juan Pablo Bastías Barahona", email = "jbastias@utem.cl", url = "https://utem.cl"
                )
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConf {

}
