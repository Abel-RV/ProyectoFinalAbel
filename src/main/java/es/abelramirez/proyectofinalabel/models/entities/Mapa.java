package es.abelramirez.proyectofinalabel.models.entities;

import es.abelramirez.proyectofinalabel.models.enums.TipoSala;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "mapa")
@AllArgsConstructor
@NoArgsConstructor
public class Mapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoSala tipoSala;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "objeto_id")
    private Objeto objeto;

}