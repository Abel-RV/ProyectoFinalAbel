package es.abelramirez.proyectofinalabel.repositories;

import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnemigoRepository extends JpaRepository<Enemigo, Long> {
}