package proyectoColegio.domain.dto.profesor;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link proyectoColegio.persistance.entity.profesor.Profesor}
 */

@Getter @Setter @NoArgsConstructor  @AllArgsConstructor
public class ProfesorDto implements Serializable {
    String dni;
    String nombre;
    String apellido;
    @Email
    String email;
    String materia;
    String grado;

    String password;
}