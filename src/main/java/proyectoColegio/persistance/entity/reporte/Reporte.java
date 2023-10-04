package proyectoColegio.persistance.entity.reporte;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import proyectoColegio.persistance.entity.estudiante.Estudiante;
import proyectoColegio.persistance.entity.profesor.Profesor;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    private String descripcion;


    @Column(name = "fecha_creacion", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(targetEntity = Profesor.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_dni", nullable = false, updatable = false)
    private Profesor profesor;

    @ManyToOne(targetEntity = Estudiante.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_estudiante", referencedColumnName = "dni")
    private Estudiante estudiante;
}