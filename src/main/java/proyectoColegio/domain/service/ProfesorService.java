package proyectoColegio.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyectoColegio.persistance.entity.profesor.Profesor;
import proyectoColegio.persistance.repository.IProfesorPaginRepository;
import proyectoColegio.persistance.repository.IProfesorRepository;

@Service    @Transactional
@RequiredArgsConstructor
public class ProfesorService {
    private final IProfesorRepository profesorRepository;
    private final IProfesorPaginRepository profesorPaginRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public Profesor saveProfesor(Profesor profesor) {

        // encriptando la passwd
        String passwd = passwordEncoder.encode(profesor.getPassword());
        profesor.setPassword(passwd);

        return this.profesorRepository.save(profesor);
    }

}
