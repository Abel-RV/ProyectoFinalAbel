package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.EnemigoRequest;
import es.abelramirez.proyectofinalabel.dto.response.EnemigoResponse;
import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import es.abelramirez.proyectofinalabel.mappers.request.EnemigoMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.EnemigoMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Enemigo;
import es.abelramirez.proyectofinalabel.repositories.EnemigoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnemigoService {
    private final EnemigoRepository objetoRepository;
    private final EnemigoMapperRequest objetoMapperRequest;
    private final EnemigoMapperResponse objetoMapperResponse;

    public EnemigoService(EnemigoRepository objetoRepository, EnemigoMapperRequest objetoMapperRequest, EnemigoMapperResponse objetoMapperResponse) {
        this.objetoRepository = objetoRepository;
        this.objetoMapperRequest = objetoMapperRequest;
        this.objetoMapperResponse = objetoMapperResponse;
    }

    public List<EnemigoResponse> findAll(){
        return objetoRepository.findAll().stream().map(objetoMapperResponse::toDto).collect(Collectors.toList());
    }

    public EnemigoRequest create(EnemigoRequest request){
        Enemigo obj1 = objetoMapperRequest.toEntity(request);
        Enemigo objNuevo = objetoRepository.save(obj1);
        return objetoMapperRequest.toDto(objNuevo);
    }

    public EnemigoResponse findById(Long id){
        return objetoRepository.findById(id).map(objetoMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        objetoRepository.deleteById(id);
    }

    public EnemigoResponse update(Long id, EnemigoRequest request){
        Enemigo obj1 = objetoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        objetoMapperRequest.partialUpdate(request,obj1);
        Enemigo nuevo = objetoRepository.save(obj1);
        return objetoMapperResponse.toDto(nuevo);
    }

}
