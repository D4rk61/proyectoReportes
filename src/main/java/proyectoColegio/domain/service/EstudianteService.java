package proyectoColegio.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoColegio.config.ModelMapperConfig;
import proyectoColegio.domain.dto.AddContactosDTO;
import proyectoColegio.email.domain.service.email.EmailServiceImpl;
import proyectoColegio.persistance.entity.estudiante.Estudiante;
import proyectoColegio.persistance.repository.IEstudianteRepository;

import javax.swing.*;
import java.util.List;

@Service    @Transactional
@RequiredArgsConstructor
public class EstudianteService {

    private final IEstudianteRepository estudianteRepository;
    private final ModelMapperConfig modelMapper;
    private final EmailServiceImpl emailService;

    public Estudiante save(Estudiante estudiante) {

        if(this.estudianteRepository.existsById(estudiante.getDni())) {
            throw new RuntimeException("El estudiante ya existe");
        }

        if(estudiante.getEmailPersonal().isEmpty()) {
            throw new RuntimeException("El email personal no puede estar vacio");
        }

        return this.estudianteRepository.save(estudiante);
    }


    // metodo put osea actualizar
    public Estudiante agregarContactos(String dniEstudiante, AddContactosDTO addContactosDTO) {

        Estudiante estudiante = this.estudianteRepository.findById(dniEstudiante).orElseThrow(() -> new RuntimeException("El estudiante no existe"));

        // envio de email temp:(lista de correos en el dto addContactos)

        // emailService.sendEmail(addContactosDTO.getEmailContacto(), "Correo de prueba", "Mensaje de prueba");
        String to[] = addContactosDTO.getEmailContacto().toArray(new String[0]);
        String subject = "Correo de confirmacion de tutor del estudiante: " + estudiante.getNombre();

        String body = "Le informamos que su email ha sido enlazado a su hij@ correctamente," +
                " cualquier reporte o inconveniente sera enviado por este medio, Feliz dia!";


        emailService.sendEmail(to, subject, body);

        estudiante.setEmailContacto(addContactosDTO.getEmailContacto());

        this.estudianteRepository.save(estudiante);

        return estudiante;
    }

}
