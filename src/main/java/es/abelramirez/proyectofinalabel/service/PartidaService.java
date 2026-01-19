package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.PartidaRequest;
import es.abelramirez.proyectofinalabel.dto.response.PartidaResponse;
import es.abelramirez.proyectofinalabel.mappers.request.PartidaMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.PartidaMapperReponse;
import es.abelramirez.proyectofinalabel.models.entities.Partida;
import es.abelramirez.proyectofinalabel.repositories.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidaService {
    private final PartidaRepository objetoRepository;
    private final PartidaMapperRequest objetoMapperRequest;
    private final PartidaMapperReponse objetoMapperResponse;

    public List<PartidaResponse> findAll(){
        return objetoRepository.findAll().stream().map(objetoMapperResponse::toDto).collect(Collectors.toList());
    }

    public PartidaRequest create(PartidaRequest request){
        Partida obj1 = objetoMapperRequest.toEntity(request);
        Partida objNuevo = objetoRepository.save(obj1);
        return objetoMapperRequest.toDto(objNuevo);
    }

    public PartidaResponse findById(Long id){
        return objetoRepository.findById(id).map(objetoMapperResponse::toDto).orElseThrow();
    }

    public void delete(Long id){
        objetoRepository.deleteById(id);
    }

    public PartidaResponse update(Long id, PartidaRequest request){
        Partida obj1 = objetoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        objetoMapperRequest.partialUpdate(request,obj1);
        Partida nuevo = objetoRepository.save(obj1);
        return objetoMapperResponse.toDto(nuevo);
    }

}
