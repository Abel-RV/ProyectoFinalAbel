package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.MapaRequest;
import es.abelramirez.proyectofinalabel.dto.response.MapaResponse;
import es.abelramirez.proyectofinalabel.mappers.request.MapaMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.MapaMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Mapa;
import es.abelramirez.proyectofinalabel.repositories.MapaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapaService {
    private final MapaRepository objetoRepository;
    private final MapaMapperRequest objetoMapperRequest;
    private final MapaMapperResponse objetoMapperResponse;

    public List<MapaResponse> findAll(){
        return objetoRepository.findAll().stream().map(objetoMapperResponse::toDto).collect(Collectors.toList());
    }

    public MapaRequest create(MapaRequest request){
        Mapa obj1 = objetoMapperRequest.toEntity(request);
        Mapa objNuevo = objetoRepository.save(obj1);
        return objetoMapperRequest.toDto(objNuevo);
    }

    public MapaResponse findById(Long id){
        return objetoRepository.findById(id).map(objetoMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        objetoRepository.deleteById(id);
    }

    public MapaResponse update(Long id, MapaRequest request){
        Mapa obj1 = objetoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        objetoMapperRequest.partialUpdate(request,obj1);
        Mapa nuevo = objetoRepository.save(obj1);
        return objetoMapperResponse.toDto(nuevo);
    }
}
