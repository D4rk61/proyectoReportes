package proyectoColegio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoColegio.persistance.entity.profesor.Profesor;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, String> {
}
