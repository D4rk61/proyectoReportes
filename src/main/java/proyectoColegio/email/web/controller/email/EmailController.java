package proyectoColegio.email.web.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyectoColegio.email.domain.dto.email.EmailFileMultDTO;
import proyectoColegio.email.domain.dto.email.EmailFileOneDTO;
import proyectoColegio.email.domain.dto.email.EmailMultDTO;
import proyectoColegio.email.domain.dto.email.EmailOneDTO;
import proyectoColegio.email.domain.service.email.EmailServiceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class EmailController {

    @Autowired
    private final EmailServiceImpl emailService;

    public EmailController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }


    /*
            Envio de emails para un remitente
     */

    @PostMapping("/sendMessageOne")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailOneDTO emailDTO){

        System.out.println("Mensaje Recibido " + emailDTO);

        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("estado", "Enviado");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendMessageFileOne")
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileOneDTO emailFileDTO){

        try {
            String fileName = emailFileDTO.getFile().getOriginalFilename();

            Path path = Paths.get("src/mail/resources/files/" + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.sendEmailWithFile(emailFileDTO.getToUser(), emailFileDTO.getSubject(), emailFileDTO.getMessage(), file);

            Map<String, String> response = new HashMap<>();
            response.put("estado", "Enviado");
            response.put("archivo", fileName);

            return ResponseEntity.ok(response);

        } catch (Exception e){
            throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }
    }

    /*
            Envio de emails para una lista de remitentes
     */

    @PostMapping("/sendMessageMult")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailMultDTO emailDTO){

        System.out.println("Mensaje Recibido " + emailDTO);

        emailService.sendEmail(emailDTO.getToUser(), emailDTO.getSubject(), emailDTO.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("estado", "Enviado");

        return ResponseEntity.ok(response);
    }


    @PostMapping("/sendMessageFileMult")
    public ResponseEntity<?> receiveRequestEmailWithFile(@ModelAttribute EmailFileMultDTO emailFileDTO){

        try {
            String fileName = emailFileDTO.getFile().getOriginalFilename();

            Path path = Paths.get("src/mail/resources/files/" + fileName);

            Files.createDirectories(path.getParent());
            Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();

            emailService.sendEmailWithFile(emailFileDTO.getToUser(), emailFileDTO.getSubject(), emailFileDTO.getMessage(), file);

            Map<String, String> response = new HashMap<>();
            response.put("estado", "Enviado");
            response.put("archivo", fileName);

            return ResponseEntity.ok(response);

        } catch (Exception e){
            throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
        }
    }
}
