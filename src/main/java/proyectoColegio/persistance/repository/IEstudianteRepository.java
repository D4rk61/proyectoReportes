package proyectoColegio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoColegio.persistance.entity.estudiante.Estudiante;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, String> {

}
