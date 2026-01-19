package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.ObjetoRequest;
import es.abelramirez.proyectofinalabel.dto.response.ObjetoResponse;
import es.abelramirez.proyectofinalabel.mappers.request.ObjetoMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.ObjetoMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Objeto;
import es.abelramirez.proyectofinalabel.repositories.ObjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjetoService {
    private final ObjetoRepository objetoRepository;
    private final ObjetoMapperRequest objetoMapperRequest;
    private final ObjetoMapperResponse objetoMapperResponse;

    public List<ObjetoResponse> findAll(){
        return objetoRepository.findAll().stream().map(objetoMapperResponse::toDto).collect(Collectors.toList());
    }

    public ObjetoRequest create(ObjetoRequest request){
        Objeto obj1 = objetoMapperRequest.toEntity(request);
        Objeto objNuevo = objetoRepository.save(obj1);
        return objetoMapperRequest.toDto(objNuevo);
    }

    public ObjetoResponse findById(Long id){
        return objetoRepository.findById(id).map(objetoMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        objetoRepository.deleteById(id);
    }

    public ObjetoResponse update(Long id, ObjetoRequest request){
        Objeto obj1 = objetoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        objetoMapperRequest.partialUpdate(request,obj1);
        Objeto nuevo = objetoRepository.save(obj1);
        return objetoMapperResponse.toDto(nuevo);
    }

}
