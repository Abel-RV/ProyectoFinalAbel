package es.abelramirez.proyectofinalabel.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "objeto")
@NoArgsConstructor
@AllArgsConstructor
public class Objeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "objeto", orphanRemoval = true)
    private List<Mapa> mapas = new ArrayList<>();

    @ManyToMany(mappedBy = "objetos", cascade = CascadeType.PERSIST)
    private List<Partida> partidas = new ArrayList<>();

}