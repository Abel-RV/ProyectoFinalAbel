package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.dto.request.CategoriaRequest;
import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.dto.response.CategoriaResponse;
import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import es.abelramirez.proyectofinalabel.repositories.CategoriaRepository;
import es.abelramirez.proyectofinalabel.service.CategoriaService;
import es.abelramirez.proyectofinalabel.service.ObjetoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaRequest> post(@RequestBody CategoriaRequest categoriaRequest) {
        return ResponseEntity.ok(categoriaService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> put(@PathVariable Long id,CategoriaRequest categoriaRequest) {
        return ResponseEntity.ok(categoriaService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
