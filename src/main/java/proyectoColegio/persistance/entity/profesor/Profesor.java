package proyectoColegio.persistance.entity.profesor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import proyectoColegio.persistance.entity.reporte.Reporte;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "profesor")
public class Profesor {
    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Email
    private String email; // -> se usara como usuario

    @Column(name = "materia")
    private String materia;

    @Column(name = "grado_guia")
    private String grado;

    // para el tema de spring security

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USER;

    @Column(name = "cantidad_reportes_enviados")
    private Integer cantidadReportesEnviados = 0;

    @OneToMany(mappedBy = "profesor", targetEntity = Reporte.class, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reporte> reportes;
}
