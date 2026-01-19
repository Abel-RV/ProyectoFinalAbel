package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.service.ObjetoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objeto")
public class ObjetoController {
    private ObjetoService enemigoService;

    @GetMapping
    public ResponseEntity<List<ObjetoResponse>> getAll() {
        return ResponseEntity.ok(enemigoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoResponse> getById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(enemigoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ObjetoRequest> post(@RequestBody ObjetoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetoResponse> put(@PathVariable Long id,ObjetoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enemigoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
