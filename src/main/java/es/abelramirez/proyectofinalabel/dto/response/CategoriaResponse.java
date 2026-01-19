package es.abelramirez.proyectofinalabel.dto.response;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Categoria}
 */
@Value
public class CategoriaResponse implements Serializable {
    Long id;
    String nombreCategoria;
    List<ObjetoDto> objetos;

    /**
     * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Objeto}
     */
    @Value
    public static class ObjetoDto implements Serializable {
        Long id;
        String nombre;
        String descripcion;
    }
}