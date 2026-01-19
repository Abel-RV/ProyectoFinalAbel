package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.PersonajeRequest;
import es.abelramirez.proyectofinalabel.dto.response.PersonajeResponse;
import es.abelramirez.proyectofinalabel.service.PersonajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personajes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonajeController {
    private PersonajeService enemigoService;

    @GetMapping
    public ResponseEntity<List<PersonajeResponse>> getAll() {
        return ResponseEntity.ok(enemigoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enemigoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonajeRequest> post(@RequestBody PersonajeRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeResponse> put(@PathVariable Long id,PersonajeRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enemigoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
