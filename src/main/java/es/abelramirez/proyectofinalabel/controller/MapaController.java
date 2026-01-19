package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.MapaRequest;
import es.abelramirez.proyectofinalabel.dto.response.MapaResponse;
import es.abelramirez.proyectofinalabel.service.MapaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MapaController {
    private MapaService enemigoService;

    @GetMapping
    public ResponseEntity<List<MapaResponse>> getAll() {
        return ResponseEntity.ok(enemigoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enemigoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MapaRequest> post(@RequestBody MapaRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MapaResponse> put(@PathVariable Long id,MapaRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enemigoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
