package es.abelramirez.proyectofinalabel.controller;

import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import es.abelramirez.proyectofinalabel.repositories.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private CategoriaRepository categoriaRepository;

}
