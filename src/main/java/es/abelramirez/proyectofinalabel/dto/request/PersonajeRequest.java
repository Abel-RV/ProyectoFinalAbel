package es.abelramirez.proyectofinalabel.dto.request;

import es.abelramirez.proyectofinalabel.models.enums.TipoCorazon;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link es.abelramirez.proyectofinalabel.models.entities.Personaje}
 */
@Value
public class PersonajeRequest implements Serializable {
    String nombre;
    Integer numCorazones;
    TipoCorazon tipoCorazon;
    Double ataque;
    Double velocidad;
    Double velocidadLagrimas;
    Double alcance;
    Double rango;
    Double suerte;
}