package proyectoColegio.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import proyectoColegio.config.ModelMapperConfig;
import proyectoColegio.domain.dto.AddContactosDTO;
import proyectoColegio.domain.dto.NewStudentDTO;
import proyectoColegio.domain.service.EstudianteService;
import proyectoColegio.persistance.entity.estudiante.Estudiante;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final ModelMapperConfig modelMapper;

    @PostMapping("/add")
    public ResponseEntity<Estudiante> save(@RequestBody NewStudentDTO newStudentDTO,
                                           UriComponentsBuilder uriComponentsBuilder) {

        Estudiante estudiante = modelMapper.modelMapper().map(newStudentDTO, Estudiante.class);

        URI uri = uriComponentsBuilder.path("/api/estudiante/getByDni")
                .buildAndExpand(estudiante.getDni()).toUri();

        return ResponseEntity.created(uri).body(estudianteService.save(estudiante));

    }

    @PutMapping("/updateContactos")
    public ResponseEntity<?> updateContactos(@RequestParam String dniEstudiante,
            @RequestBody AddContactosDTO addContactosDTO) {

        Estudiante estudiante = this.estudianteService.agregarContactos(dniEstudiante, addContactosDTO);

        return ResponseEntity.ok(estudiante);
    }
}
