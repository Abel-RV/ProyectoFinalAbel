package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.TipoCorazon;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Personaje}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeRequest implements Serializable {
    String nombre;

    @Max(12)
    Integer numCorazones;
    TipoCorazon tipoCorazon;
    Double ataque;
    Double velocidad;
    Double velocidadLagrimas;
    Double alcance;
    Double rango;
    Double suerte;
}