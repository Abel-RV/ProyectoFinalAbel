package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.PersonajeRequest;
import es.abelramirez.proyectofinalabel.dto.response.PersonajeResponse;
import es.abelramirez.proyectofinalabel.mappers.request.PersonajeMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.PersonajeMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Personaje;
import es.abelramirez.proyectofinalabel.repositories.PersonajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonajeService {
    private final PersonajeRepository objetoRepository;
    private final PersonajeMapperRequest objetoMapperRequest;
    private final PersonajeMapperResponse objetoMapperResponse;

    public PersonajeService(PersonajeMapperResponse objetoMapperResponse, PersonajeMapperRequest objetoMapperRequest, PersonajeRepository objetoRepository) {
        this.objetoMapperResponse = objetoMapperResponse;
        this.objetoMapperRequest = objetoMapperRequest;
        this.objetoRepository = objetoRepository;
    }

    public List<PersonajeResponse> findAll(){
        return objetoRepository.findAll().stream().map(objetoMapperResponse::toDto).collect(Collectors.toList());
    }

    public PersonajeRequest create(PersonajeRequest request){
        Personaje obj1 = objetoMapperRequest.toEntity(request);
        Personaje objNuevo = objetoRepository.save(obj1);
        return objetoMapperRequest.toDto(objNuevo);
    }

    public PersonajeResponse findById(Long id){
        return objetoRepository.findById(id).map(objetoMapperResponse::toDto).orElseThrow();
    }

    public void delete(Long id){
        objetoRepository.deleteById(id);
    }

    public PersonajeResponse update(Long id, PersonajeRequest request){
        Personaje obj1 = objetoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        objetoMapperRequest.partialUpdate(request,obj1);
        Personaje nuevo = objetoRepository.save(obj1);
        return objetoMapperResponse.toDto(nuevo);
    }

}
