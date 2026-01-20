package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.service.ObjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ObjetoController {
    private final ObjetoService enemigoService;

    @GetMapping
    public ResponseEntity<Page<ObjetoResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(enemigoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoResponse> getById(@PathVariable Long id) {
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
