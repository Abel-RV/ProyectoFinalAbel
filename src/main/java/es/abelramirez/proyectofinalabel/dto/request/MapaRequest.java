package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.TipoSala;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Mapa}
 */
@Value
public class MapaRequest implements Serializable {
    @NotNull(message = "Ingrese el nombre del mapa")
    String nombre;
    @NotNull
    TipoSala tipoSala;
}