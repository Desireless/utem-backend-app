package cl.utem.inf.backend.services;

import cl.utem.inf.backend.enums.Profile;
import cl.utem.inf.backend.exceptions.BadCredentialException;
import cl.utem.inf.backend.models.User;
import cl.utem.inf.backend.repositories.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Servicio de usuario.
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Service
public class UserService {

    /**
     * Identificador de cliente de google.
     */
    @Value("${google.client.id}")
    private String clientId;

    /**
     * Repositorio de usuario.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Verificador de google id.
     */
    private GoogleIdTokenVerifier verifier = null;

    /**
     * Logger de la clase.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * inicializa el servicio de GoogleIdtoken, evitando la creación de un nuevo objeto cada vez que se llama al servicio.
     *
     */
    @PostConstruct
    public void initService() {
        try {
            this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),
                    new GsonFactory()).setAudience(Collections.singletonList(clientId))
                    .build();
        } catch (Exception e) {
            LOGGER.error("Error al iniciar el verificador de google: {}", e.getLocalizedMessage());
            LOGGER.debug("Error al iniciar el verificador de google: {}", e.getMessage(), e);
        }
    }


    /**
     * Recibe token de authorization y autentica al usuario.
     *
     * @return usuario.
     */
    public User authUser(final String authorization) {
        User user = null;
        try {
            if (StringUtils.isNotBlank(authorization)) {
                final String jwt = StringUtils.trimToEmpty(StringUtils.removeStartIgnoreCase(authorization, "bearer"));
                final GoogleIdToken google = verifier.verify(jwt);
                if (google == null) {
                    throw new BadCredentialException("NO se pudo decodificar los datos de google");
                }

                if (!google.verifyExpirationTime(System.currentTimeMillis(), 5)) {
                    throw new BadCredentialException("Token expirado.");
                }

                final boolean emailVerified = google.getPayload().getEmailVerified();
                if (!emailVerified) {
                    String name = (String) google.getPayload().get("name");
                    throw new BadCredentialException(String.format("El correo electrónico de %s no está validado", name));
                }

                final String email = StringUtils.lowerCase(StringUtils.trimToEmpty(google.getPayload().getEmail()));
                if (!EmailValidator.getInstance().isValid(email)) {
                    throw new BadCredentialException(String.format("El correo electrónico %s no es válido", email));
                }

                if (!StringUtils.containsIgnoreCase(email, "@utem.cl")) {
                    throw new BadCredentialException(String.format("El correo electrónico %s no pertenece al dominio de la utem", email));
                }

                user = userRepository.findByEmailIgnoreCase(email);
                if (user == null) {
                    User utem = new User();
                    utem.setEmail(email);
                    utem.setProfile(Profile.STUDENT);
                    user = userRepository.saveAndFlush(utem);
                }
            }
        } catch (Exception e) {
            user = null;
            LOGGER.error("Error al validar jwt y obtener email: {}", e.getLocalizedMessage());
            LOGGER.debug("Error al validar jwt y obtener email: {}", e.getMessage(), e);
        }
        return user;
    }
}
