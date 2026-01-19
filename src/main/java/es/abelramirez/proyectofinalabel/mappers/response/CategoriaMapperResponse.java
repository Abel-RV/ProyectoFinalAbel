package es.abelramirez.proyectofinalabel.mappers.response;

import es.abelramirez.proyectofinalabel.dto.response.CategoriaResponse;
import es.abelramirez.proyectofinalabel.models.entities.Categoria;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapperResponse {
    Categoria toEntity(CategoriaResponse categoriaResponse);



    CategoriaResponse toDto(Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Categoria partialUpdate(CategoriaResponse categoriaResponse, @MappingTarget Categoria categoria);
}