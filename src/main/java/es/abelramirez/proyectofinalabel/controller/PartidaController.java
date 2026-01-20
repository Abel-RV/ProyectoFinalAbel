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
    private final PartidaService partidaService;

    @GetMapping
    public ResponseEntity<List<PartidaResponse>> getAll() {
        return ResponseEntity.ok(partidaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponse> getById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(partidaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PartidaRequest> post(@RequestBody PartidaRequest categoriaRequest) {
        return ResponseEntity.ok(partidaService.create(categoriaRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidaResponse> put(@Valid @PathVariable Long id,@RequestBody PartidaRequest categoriaRequest) {
        return ResponseEntity.ok(partidaService.update(id,categoriaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        partidaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/objetos/{idObjeto}")
    public ResponseEntity<Void> addObjeto(@PathVariable Long id, @PathVariable Long idObjeto) {

        partidaService.anadirObjeto(id, idObjeto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/enemigos/{idEnemigo}")
    public ResponseEntity<Void> addEnemigo(@PathVariable Long id, @PathVariable Long idEnemigo) {
        partidaService.anadirEnemigo(id, idEnemigo);
        return ResponseEntity.ok().build();
    }
}
