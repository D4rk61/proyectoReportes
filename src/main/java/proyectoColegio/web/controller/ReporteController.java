package proyectoColegio.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import proyectoColegio.config.ModelMapperConfig;
import proyectoColegio.domain.dto.reporte.NewReporteDTO;
import proyectoColegio.domain.service.ReporteService;
import proyectoColegio.email.domain.service.email.EmailServiceImpl;
import proyectoColegio.persistance.entity.reporte.Reporte;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    private final ReporteService reporteService;
    private final ModelMapperConfig modelMapper;

    @PostMapping("/add")
    public ResponseEntity<Reporte> save(@RequestParam String dniAlumno, @RequestParam String dniProfesor, @RequestBody NewReporteDTO newReporteDTO,
                                        UriComponentsBuilder uriComponentsBuilder) {


        Reporte reporte = modelMapper.modelMapper().map(newReporteDTO, Reporte.class);

        URI uri = uriComponentsBuilder.path("/api/reporte/{id}").buildAndExpand(reporte.getId()).toUri();


        return ResponseEntity.created(uri).body(this.reporteService.saveWithParams(dniAlumno, dniProfesor, reporte));

    }
}
