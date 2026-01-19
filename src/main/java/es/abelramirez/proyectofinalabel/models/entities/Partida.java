package es.abelramirez.proyectofinalabel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.abelramirez.proyectofinalabel.models.enums.EstadoJugador;
import es.abelramirez.proyectofinalabel.models.enums.TipoJuego;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "partida")
@AllArgsConstructor
@NoArgsConstructor
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoJuego tipoJuego;

    @Enumerated(EnumType.STRING)
    private EstadoJugador estadoJugador;
    private LocalDateTime fechaPartida;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personaje_id")
    private Personaje personaje;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @JoinTable(name = "partida_enemigos",
            joinColumns = @JoinColumn(name = "partida_id"),
            inverseJoinColumns = @JoinColumn(name = "enemigos_id"))
    private List<Enemigo> enemigos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinTable(name = "partida_objetos",
            joinColumns = @JoinColumn(name = "partida_id"),
            inverseJoinColumns = @JoinColumn(name = "objetos_id"))
    private List<Objeto> objetos = new ArrayList<>();

}