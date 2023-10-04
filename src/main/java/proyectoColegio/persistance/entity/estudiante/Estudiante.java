package proyectoColegio.persistance.entity.estudiante;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import proyectoColegio.persistance.entity.reporte.Reporte;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor  @AllArgsConstructor
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    private String nombre;

    private String apellido;

    private String grado;

    private Integer edad;
    // aqui se realizara una relacion de que un estudiante solo puede pertenecer a una carrera
    // puedo hacerlo con un enum?

    @Enumerated(EnumType.STRING)
    private Carrera carrera;


    // tema de email's

    @Column(name = "email_personal")
    @Email
    private String emailPersonal;

    // una lista de correos de familiares, un alumno puede tener varios correos de familiares, relacion de
    // uno a muchos

    @ElementCollection
    @CollectionTable(name = "email_contacto",
            joinColumns = @JoinColumn(name = "dni_estudiante"))
    @Column(name = "email")
    private List<String> emailContacto;

    @Column(name = "cantidad_reporte")
    private Integer cantidadReporte = 0; // cantidad de reportes

    /*
    @OneToMany(targetEntity = Reporte.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_estudiante")  // nombre de la columna en la tabla Reporte que referencia al estudiante
    private List<Reporte> reportes;

     */
}