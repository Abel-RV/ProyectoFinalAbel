package es.abelramirez.proyectofinalabel.models.entities;

import es.abelramirez.proyectofinalabel.models.enums.TipoEnemigo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "enemigo")
public class Enemigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombreEnemigo;

    private Long vida;

    private TipoEnemigo tipo;

    @ManyToMany(mappedBy = "enemigos")
    private List<Partida> partidas = new ArrayList<>();

}