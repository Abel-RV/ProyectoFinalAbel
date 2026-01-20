package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.PartidaRequest;
import es.abelramirez.proyectofinalabel.dto.response.PartidaResponse;
import es.abelramirez.proyectofinalabel.service.PartidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PartidaController {
    private final PartidaService enemigoService;

    @GetMapping
    public ResponseEntity<List<PartidaResponse>> getAll() {
        return ResponseEntity.ok(enemigoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enemigoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PartidaRequest> post(@RequestBody PartidaRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidaResponse> put(@PathVariable Long id,PartidaRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enemigoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
