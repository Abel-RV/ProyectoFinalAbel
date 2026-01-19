package es.abelramirez.proyectofinalabel.config;

import es.abelramirez.proyectofinalabel.models.entities.*;
import es.abelramirez.proyectofinalabel.models.enums.*;
import es.abelramirez.proyectofinalabel.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ObjetoRepository objetoRepository;
    private final PersonajeRepository personajeRepository;
    private final EnemigoRepository enemigoRepository;
    private final JugadorRepository jugadorRepository;
    private final PartidaRepository partidaRepository;
    private final MapaRepository mapaRepository;

    public DataInitializer(CategoriaRepository categoriaRepository,
                           ObjetoRepository objetoRepository,
                           PersonajeRepository personajeRepository,
                           EnemigoRepository enemigoRepository,
                           JugadorRepository jugadorRepository,
                           PartidaRepository partidaRepository,
                           MapaRepository mapaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.objetoRepository = objetoRepository;
        this.personajeRepository = personajeRepository;
        this.enemigoRepository = enemigoRepository;
        this.jugadorRepository = jugadorRepository;
        this.partidaRepository = partidaRepository;
        this.mapaRepository = mapaRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Evitamos duplicar datos si ya existen
        if (personajeRepository.count() > 0) {
            System.out.println("⚠️ La base de datos ya tiene datos. Omitiendo carga inicial.");
            return;
        }

        System.out.println("---- 🚀 INICIANDO CARGA DE DATOS MANUAL (SIMPLIFICADA) ----");

        // 1. CATEGORÍAS
        Categoria catPasivos = new Categoria();
        catPasivos.setNombreCategoria("Objetos Pasivos");

        Categoria catActivos = new Categoria();
        catActivos.setNombreCategoria("Objetos Activos");

        Categoria catFamiliares = new Categoria();
        catFamiliares.setNombreCategoria("Familiares");

        categoriaRepository.saveAll(Arrays.asList(catPasivos, catActivos, catFamiliares));

        // 2. OBJETOS (ITEMS)
        Objeto brimstone = new Objeto();
        brimstone.setNombre("Brimstone");
        brimstone.setCategoria(catPasivos); // Relación 1:N

        Objeto d6 = new Objeto();
        d6.setNombre("The D6");
        d6.setCategoria(catActivos);

        Objeto godhead = new Objeto();
        godhead.setNombre("Godhead");
        godhead.setCategoria(catPasivos);

        Objeto brotherBobby = new Objeto();
        brotherBobby.setNombre("Brother Bobby");
        brotherBobby.setCategoria(catFamiliares);

        objetoRepository.saveAll(Arrays.asList(brimstone, d6, godhead, brotherBobby));

        // 3. PERSONAJES
        Personaje isaac = Personaje.builder()
                .nombre("Isaac")
                .numCorazones(3)
                .tipoCorazon(TipoCorazon.CORAZON_ROJO)
                .ataque(3.5)
                .velocidad(1.0)
                .suerte(0.0)
                .rango(23.75)
                .velocidadLagrimas(1.0)
                .alcance(23.75)
                .build();

        Personaje azazel = Personaje.builder()
                .nombre("Azazel")
                .numCorazones(3)
                .tipoCorazon(TipoCorazon.CORAZON_OSCURO)
                .ataque(5.5)
                .velocidad(1.25)
                .suerte(0.0)
                .rango(18.0)
                .velocidadLagrimas(0.5)
                .alcance(18.0)
                .build();

        Personaje theLost = Personaje.builder()
                .nombre("The Lost")
                .numCorazones(0)
                .tipoCorazon(TipoCorazon.CORAZON_AZUL)
                .ataque(3.5)
                .velocidad(1.0)
                .suerte(-1.0)
                .rango(23.75)
                .velocidadLagrimas(1.0)
                .alcance(23.75)
                .build();

        personajeRepository.saveAll(Arrays.asList(isaac, azazel, theLost));

        // 4. ENEMIGOS
        Enemigo monstro = new Enemigo();
        monstro.setNombreEnemigo("Monstro");
        monstro.setVida(250L);
        monstro.setTipo(TipoEnemigo.JEFE_PISO);

        Enemigo mom = new Enemigo();
        mom.setNombreEnemigo("Mom");
        mom.setVida(600L);
        mom.setTipo(TipoEnemigo.JEFE_FINAL);

        Enemigo duke = new Enemigo();
        duke.setNombreEnemigo("Duke of Flies");
        duke.setVida(110L);
        duke.setTipo(TipoEnemigo.JEFE_PISO);

        enemigoRepository.saveAll(Arrays.asList(monstro, mom, duke));

        // 5. JUGADORES
        Jugador abel = Jugador.builder()
                .nombre("Abel")
                .email("abel@bindingofisaac.com")
                .build();

        Jugador tester = Jugador.builder()
                .nombre("Tester")
                .email("test@example.com")
                .build();

        jugadorRepository.saveAll(Arrays.asList(abel, tester));

        // 6. PARTIDAS (Relaciones N:M y Transacciones)

        // --- Partida 1: Abel gana con Isaac ---
        Partida partida1 = new Partida();
        partida1.setJugador(abel);
        partida1.setPersonaje(isaac);
        // Recuerda: Debes haber añadido el campo 'fechaPartida' en tu entidad Partida
        partida1.setFechaPartida(LocalDateTime.now().minusDays(1));
        partida1.setTipoJuego(TipoJuego.NORMAL);
        partida1.setEstadoJugador(EstadoJugador.VICTORIA);

        // Items conseguidos (N:M)
        partida1.getObjetos().add(d6);
        partida1.getObjetos().add(godhead);

        // Bosses derrotados (N:M)
        partida1.getEnemigos().add(monstro);
        partida1.getEnemigos().add(mom);

        partidaRepository.save(partida1);

        // --- Partida 2: Tester muere con Azazel ---
        Partida partida2 = new Partida();
        partida2.setJugador(tester);
        partida2.setPersonaje(azazel);
        partida2.setFechaPartida(LocalDateTime.now().minusHours(5));
        partida2.setTipoJuego(TipoJuego.DIFICIL);
        partida2.setEstadoJugador(EstadoJugador.MUERTO);

        partida2.getObjetos().add(brimstone); // Empezó bien...
        partida2.getEnemigos().add(duke); // ...pero murió luego

        partidaRepository.save(partida2);

        // 7. MAPAS (Opcional - Item Pools)
        Mapa salaTesoro = new Mapa();
        salaTesoro.setNombre("Sótano I - Sala del Tesoro");
        salaTesoro.setTipoSala(TipoSala.SOTANO);
        salaTesoro.setObjeto(brimstone); // Aquí apareció el Brimstone

        mapaRepository.save(salaTesoro);

        System.out.println("---- ✅ CARGA DE DATOS COMPLETADA CON ÉXITO ----");
    }
}

