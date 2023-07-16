package cl.utem.inf.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "campus")
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "faculty_name", nullable = false)
    private String facultyName;

    @Column(name = "campus_name", nullable = false)
    private String campusName;

    @Column(name = "commune_name", nullable = false)
    private String communeName;

    // Constructor vacío (requerido por JPA)
    public Campus() {
    }

    // Constructor
    public Campus(String facultyName, String campusName, String communeName) {
        this.facultyName = facultyName;
        this.campusName = campusName;
        this.communeName = communeName;
    }
}
