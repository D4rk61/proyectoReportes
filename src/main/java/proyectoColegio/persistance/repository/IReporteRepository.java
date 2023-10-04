package proyectoColegio.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoColegio.persistance.entity.reporte.Reporte;

@Repository
public interface IReporteRepository extends JpaRepository<Reporte, Long> {
}
