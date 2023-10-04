package proyectoColegio.domain.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoColegio.config.ModelMapperConfig;
import proyectoColegio.email.domain.service.email.EmailServiceImpl;
import proyectoColegio.persistance.entity.estudiante.Estudiante;
import proyectoColegio.persistance.entity.profesor.Profesor;
import proyectoColegio.persistance.entity.reporte.Reporte;
import proyectoColegio.persistance.repository.IEstudianteRepository;
import proyectoColegio.persistance.repository.IProfesorRepository;
import proyectoColegio.persistance.repository.IReporteRepository;

import java.time.format.DateTimeFormatter;

@Service  @Transactional
@RequiredArgsConstructor
public class ReporteService {

    private final IReporteRepository repository;
    private final ModelMapperConfig modelMapper;
    private final EmailServiceImpl emailService;
    private final IEstudianteRepository estudianteRepository;
    private final IProfesorRepository profesorRepository;

    public Reporte saveWithParams(String dniEstudiante, String dniProfesor, Reporte reporte) {
        // Buscar el estudiante por su dni y manejar el caso de que no se encuentre
        Estudiante estudiante = estudianteRepository.findById(dniEstudiante)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado"));

        Profesor profesor = profesorRepository.findById(dniProfesor)
                .orElseThrow(() -> new EntityNotFoundException("Profesor no encontrado"));

        // Asignar el estudiante al reporte

        reporte.setEstudiante(estudiante);
        reporte.setProfesor(profesor);

        // Realizar el resto de la lógica

        if (estudiante != null) {
            Integer cantidadReporte = estudiante.getCantidadReporte() + 1;
            estudiante.setCantidadReporte(cantidadReporte);

            // formateando la fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedFechaCreacion = reporte.getFechaCreacion().format(formatter);
            // Realiza cualquier otra lógica que necesites con el estudiante

            if (estudiante.getEmailContacto() != null) {
                String[] to = estudiante.getEmailContacto().toArray(new String[0]);
                String subject = "Reporte de Estudiante, Motivo: " + reporte.getTitulo();
                String body =

                        "Estimado/a tutor del estudiante " + estudiante.getNombre() + ",\n" +
                        "enviamos este reporte por la siguiente descripción: " + reporte.getDescripcion() + "\n" +
                        "Gracias por su atención." + "\n\n\n" + "Información adicional: " + "\n" +
                        "Hora del reporte: " + formattedFechaCreacion + "\n" +
                        "Maestro: " + profesor.getNombre() + " " + profesor.getApellido() + "profesor/a de: " + profesor.getMateria() + "\n" +
                        "Contacto: " + profesor.getEmail();

                // Envía el correo
                emailService.sendEmail(to, subject, body);
            } else {

                System.out.println("El estudiante no tiene contactos de correo electrónico");
                return repository.save(reporte);
            }

            return repository.save(reporte);
        } else {
            String[] to = reporte.getEstudiante().getEmailContacto().toArray(new String[0]);
            String subject = "Reporte de Profesor, Motivo: " + reporte.getTitulo();

            String body = "Descripcion del reporte: " + reporte.getDescripcion();

            emailService.sendEmail(to, subject, body);

            return repository.save(reporte);
        }
    }

}

