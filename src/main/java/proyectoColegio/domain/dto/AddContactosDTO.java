package proyectoColegio.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link proyectoColegio.persistance.entity.estudiante.Estudiante}
 */
@NoArgsConstructor  @AllArgsConstructor @Getter @Setter
public class AddContactosDTO implements Serializable {
    @NotNull
    @Email
    List<String> emailContacto;
}