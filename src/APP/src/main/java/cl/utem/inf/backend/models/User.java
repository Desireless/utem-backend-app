package cl.utem.inf.backend.models;

import cl.utem.inf.backend.enums.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

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

    // Constructor vacío (requerido por JPA)
    public User() {
        this.email = "";
        this.profile = Profile.STUDENT;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    // Constructor
    public User(String email, Profile profile) {
        this.email = email;
        this.profile = profile;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
