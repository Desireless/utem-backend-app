package cl.utem.inf.backend.models;

import cl.utem.inf.backend.enums.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

/**
 * Entidad que representa a un usuario UTEM
 *
 * @author Juan Pablo Bastías Barahona <mainjpbb@gmail.com>
 */
@Entity
@Table(name = "users")
public class User extends PkEntityBase {

    /**
     * Correo electrónico de un usuario UTEM
     */
    @Column(name = "email", nullable = false)
    private String email = null;

    /**
     * Perfil del usuario UTEM
     */
    @Column(name = "profile", nullable = false)
    private Profile profile = Profile.STUDENT;

    /**
     * Constructor de User por defecto
     */
    public User() {
        this.email = "";
        this.profile = Profile.STUDENT;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    /**
     * Constructor de User
     *
     * @param name Nombre de la sala
     * @param campus id del Campus al que pertenece la sala
     */
    public User(String email, Profile profile) {
        this.email = email;
        this.profile = profile;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    /**
     *
     * @param email Correo electrónico del usuario UTEM
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return Correo electrónico del usuario UTEM
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return Perfil del usuario UTEM
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     *
     * @param profile Perfil del usuario UTEM
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
