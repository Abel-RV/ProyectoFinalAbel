package es.abelramirez.proyectofinalabel.dto.request;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Objeto}
 */
@Value
public class ObjetoRequest implements Serializable {
    String nombre;
    String descripcion;
    CategoriaDto categoria;

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Categoria}
     */
    @Value
    public static class CategoriaDto implements Serializable {
        Long id;
    }
}