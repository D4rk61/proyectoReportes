package proyectoColegio.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import proyectoColegio.persistance.entity.estudiante.Carrera;

import java.io.Serializable;

/**
 * DTO for {@link proyectoColegio.persistance.entity.estudiante.Estudiante}
 */
@NoArgsConstructor  @AllArgsConstructor @Getter @Setter
public class NewStudentDTO implements Serializable {

    @NotNull
    @NotEmpty
    @NotBlank
    String dni;
    String nombre;
    String apellido;
    String grado;
    Integer edad;
    Carrera carrera;
    @Email
    String emailPersonal;

}