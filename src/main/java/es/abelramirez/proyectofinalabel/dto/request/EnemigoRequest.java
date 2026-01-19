package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.TipoEnemigo;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Enemigo}
 */
@Value
public class EnemigoRequest implements Serializable {
    @NotNull(message = "Ingrese el nombre del enemigo")
    String nombreEnemigo;
    @NotNull(message = "Ingrese la vida del enemigo")
    Long vida;
    @NotNull(message = "Ingrese el tipo de enemigo")
    TipoEnemigo tipo;
}