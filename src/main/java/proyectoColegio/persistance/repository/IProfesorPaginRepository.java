package proyectoColegio.persistance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import proyectoColegio.persistance.entity.profesor.Profesor;


@Repository
public interface IProfesorPaginRepository extends ListPagingAndSortingRepository<Profesor, String> {
    Page<Profesor> findAll(Pageable pageable);
}
