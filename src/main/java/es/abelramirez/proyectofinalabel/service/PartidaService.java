package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.PartidaRequest;
import es.abelramirez.proyectofinalabel.dto.response.PartidaResponse;
import es.abelramirez.proyectofinalabel.mappers.request.PartidaMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.PartidaMapperReponse;
import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import es.abelramirez.proyectofinalabel.models.entities.Objeto;
import es.abelramirez.proyectofinalabel.models.entities.Partida;
import es.abelramirez.proyectofinalabel.repositories.EnemigoRepository;
import es.abelramirez.proyectofinalabel.repositories.ObjetoRepository;
import es.abelramirez.proyectofinalabel.repositories.PartidaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final ObjetoRepository objetoRepository;
    private final EnemigoRepository enemigoRepository;
    private final PartidaMapperRequest partidaMapperRequest;
    private final PartidaMapperReponse partidaMapperReponse;

    @Transactional
    public void anadirObjeto(Long idPartida, Long idObjeto) {
        Partida partida = partidaRepository.findById(idPartida)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));
        Objeto objeto = objetoRepository.findById(idObjeto)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado"));

        partida.getObjetos().add(objeto);
        partidaRepository.save(partida);
    }

    @Transactional
    public void anadirEnemigo(Long idPartida, Long idEnemigo) {
        Partida partida = partidaRepository.findById(idPartida)
                .orElseThrow(() -> new RuntimeException("Partida no encontrada"));
        Enemigo enemigo = enemigoRepository.findById(idEnemigo)
                .orElseThrow(() -> new RuntimeException("Enemigo no encontrado"));

        partida.getEnemigos().add(enemigo);
        partidaRepository.save(partida);
    }

    public List<PartidaResponse> findAll(){
        return partidaRepository.findAll().stream().map(partidaMapperReponse::toDto).collect(Collectors.toList());
    }

    public PartidaRequest create(PartidaRequest request){
        Partida obj1 = partidaMapperRequest.toEntity(request);
        Partida objNuevo = partidaRepository.save(obj1);
        return partidaMapperRequest.toDto(objNuevo);
    }

    public PartidaResponse findById(Long id){
        return partidaRepository.findById(id).map(partidaMapperReponse::toDto).orElseThrow();
    }

    public void delete(Long id){
        partidaRepository.deleteById(id);
    }

    public PartidaResponse update(Long id, PartidaRequest request){
        Partida obj1 = partidaRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        partidaMapperRequest.partialUpdate(request,obj1);
        Partida nuevo = partidaRepository.save(obj1);
        return partidaMapperReponse.toDto(nuevo);
    }

}
