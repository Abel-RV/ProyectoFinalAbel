package es.abelramirez.proyectofinalabel.mappers.request;

import es.abelramirez.proyectofinalabel.dto.request.JugadorRequest;
import es.abelramirez.proyectofinalabel.models.entities.Jugador;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface JugadorMapperRequest {
    Jugador toEntity(JugadorRequest jugadorRequest);

    JugadorRequest toDto(Jugador jugador);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Jugador partialUpdate(JugadorRequest jugadorRequest, @MappingTarget Jugador jugador);
}