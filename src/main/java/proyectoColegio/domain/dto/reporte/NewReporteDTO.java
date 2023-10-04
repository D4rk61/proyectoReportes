package proyectoColegio.domain.dto.reporte;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import proyectoColegio.persistance.entity.estudiante.Estudiante;
import proyectoColegio.persistance.entity.profesor.Profesor;

import java.io.Serializable;

/**
 * DTO for {@link proyectoColegio.persistance.entity.reporte.Reporte}
 */

@NoArgsConstructor  @AllArgsConstructor @Getter @Setter
public class NewReporteDTO implements Serializable {
    String titulo;
    String descripcion;


}