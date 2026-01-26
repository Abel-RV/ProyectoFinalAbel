package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.service.ObjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Controlador de los objetos",description = "Gestiona los objetos")
public class ObjetoController {
    private final ObjetoService enemigoService;

    @Operation(summary = "Obtiene todos los objetos",description = "GET")
    @GetMapping
    public ResponseEntity<Page<ObjetoResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(enemigoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(enemigoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ObjetoRequest> post(@RequestBody ObjetoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetoResponse> put(@Valid @PathVariable Long id,@RequestBody ObjetoRequest categoriaRequest) {
        return ResponseEntity.ok(enemigoService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        enemigoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
