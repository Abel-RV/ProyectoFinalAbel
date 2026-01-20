package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.EnemigoRequest;
import es.abelramirez.proyectofinalabel.dto.response.EnemigoResponse;
import es.abelramirez.proyectofinalabel.service.EnemigoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enemigos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EnemigoController {
    private final EnemigoService enemigoService;

    @GetMapping
    public ResponseEntity<List<EnemigoResponse>> getAll() {
        return ResponseEntity.ok(enemigoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnemigoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enemigoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EnemigoRequest> post(@RequestBody EnemigoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnemigoResponse> put(@PathVariable Long id,EnemigoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enemigoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
