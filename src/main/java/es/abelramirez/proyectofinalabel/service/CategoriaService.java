package es.abelramirez.proyectofinalabel.service;

import es.abelramirez.proyectofinalabel.dto.request.CategoriaRequest;
import es.abelramirez.proyectofinalabel.dto.response.CategoriaResponse;
import es.abelramirez.proyectofinalabel.mappers.request.CategoriaMapperRequest;
import es.abelramirez.proyectofinalabel.mappers.response.CategoriaMapperResponse;
import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import es.abelramirez.proyectofinalabel.models.entities.Objeto;
import es.abelramirez.proyectofinalabel.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository objetoRepository;
    private final CategoriaMapperRequest objetoMapperRequest;
    private final CategoriaMapperResponse objetoMapperResponse;

    public List<CategoriaResponse> findAll(){
        return objetoRepository.findAll().stream().map(objetoMapperResponse::toDto).collect(Collectors.toList());
    }

    public CategoriaRequest create(CategoriaRequest request){
        Categoria obj1 = objetoMapperRequest.toEntity(request);
        Categoria objNuevo = objetoRepository.save(obj1);
        return objetoMapperRequest.toDto(objNuevo);
    }

    public CategoriaResponse findById(Long id){
        return objetoRepository.findById(id).map(objetoMapperResponse::toDto).orElseThrow(()->new RuntimeException("Id no encontrado"));
    }

    public void delete(Long id){
        objetoRepository.deleteById(id);
    }

    public CategoriaResponse update(Long id, CategoriaRequest request){
        Categoria obj1 = objetoRepository.findById(id).orElseThrow(()->new RuntimeException("Id no encontrado"));
        objetoMapperRequest.partialUpdate(request,obj1);
        Categoria nuevo = objetoRepository.save(obj1);
        return objetoMapperResponse.toDto(nuevo);
    }
}
