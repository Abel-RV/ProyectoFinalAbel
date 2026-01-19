package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.JugadorRequest;
import es.abelramirez.proyectofinalabel.dto.response.JugadorResponse;
import es.abelramirez.proyectofinalabel.mappers.request.JugadorMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.JugadorMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Jugador;
import es.abelramirez.proyectofinalabel.repositories.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JugadorService {
    private final JugadorRepository objetoRepository;
    private final JugadorMapperRequest objetoMapperRequest;
    private final JugadorMapperResponse objetoMapperResponse;

    public List<JugadorResponse> findAll(){
        return objetoRepository.findAll().stream().map(objetoMapperResponse::toDto).collect(Collectors.toList());
    }

    public JugadorRequest create(JugadorRequest request){
        Jugador obj1 = objetoMapperRequest.toEntity(request);
        Jugador objNuevo = objetoRepository.save(obj1);
        return objetoMapperRequest.toDto(objNuevo);
    }

    public JugadorResponse findById(Long id){
        return objetoRepository.findById(id).map(objetoMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        objetoRepository.deleteById(id);
    }

    public JugadorResponse update(Long id, JugadorRequest request){
        Jugador obj1 = objetoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        objetoMapperRequest.partialUpdate(request,obj1);
        Jugador nuevo = objetoRepository.save(obj1);
        return objetoMapperResponse.toDto(nuevo);
    }
}
