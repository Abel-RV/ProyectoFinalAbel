package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.JugadorRequest;
import es.abelramirez.proyectofinalabel.dto.response.JugadorResponse;
import es.abelramirez.proyectofinalabel.service.JugadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jugadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JugadorController {
    private final JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<List<JugadorResponse>> getAll() {
        return ResponseEntity.ok(jugadorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jugadorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<JugadorRequest> post(@RequestBody JugadorRequest categoriaRequest) {
        return ResponseEntity.ok(jugadorService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JugadorResponse> put(@PathVariable Long id,JugadorRequest categoriaRequest) {
        return ResponseEntity.ok(jugadorService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
