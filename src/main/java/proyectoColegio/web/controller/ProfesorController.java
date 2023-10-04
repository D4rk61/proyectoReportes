package proyectoColegio.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import proyectoColegio.config.ModelMapperConfig;
import proyectoColegio.domain.dto.profesor.ProfesorDto;
import proyectoColegio.domain.service.ProfesorService;
import proyectoColegio.persistance.entity.profesor.Profesor;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {

    private final ProfesorService profesorService;
    private final ModelMapperConfig modelMapper;

    @PostMapping("/add")
    public ResponseEntity<Profesor> save(@RequestBody ProfesorDto profesorDto,
                                         UriComponentsBuilder uriComponentsBuilder) {


        Profesor profesor = modelMapper.modelMapper().map(profesorDto, Profesor.class);


        URI uri = uriComponentsBuilder.path("/api/profesor/{dni}")
                .buildAndExpand(profesor.getDni()).toUri();


        return ResponseEntity.created(uri).body(profesorService.saveProfesor(profesor));
    }

}
