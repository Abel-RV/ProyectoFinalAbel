package es.abelramirez.proyectofinalabel.models.entities;

import es.abelramirez.proyectofinalabel.models.enums.TipoCorazon;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "personaje")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;
    private Integer numCorazones;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TipoCorazon tipoCorazon=TipoCorazon.CORAZON_ROJO;

    private Double ataque;
    private Double velocidad;
    private Double velocidadLagrimas;
    private Double alcance;
    private Double rango;
    private Double suerte;

}