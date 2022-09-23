package ecu.project.product.persistence.mapper;


import ecu.project.product.domain.dto.MovementDTO;
import ecu.project.product.persistence.model.entity.MovementEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMovementMapper {

    @Mappings({
            @Mapping(source = "idMovement", target = "idMovement"),
            @Mapping(source = "typeMovement", target = "typeMovement"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "balance", target = "balance"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "idAccount", target = "idAccount"),
            @Mapping(source = "account", target = "account"),
    })
    MovementDTO toMovement(MovementEntity movementEntity);

    List<MovementDTO> toMovements(List<MovementEntity> movementEntities);

    @InheritInverseConfiguration

    MovementEntity toMovementEntity(MovementDTO client);

}
