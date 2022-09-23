package ecu.project.product.persistence.mapper;



import ecu.project.product.domain.dto.AccountDTO;
import ecu.project.product.persistence.model.entity.AccountEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    @Mappings({
            @Mapping(source = "idAccount", target = "idAccount"),
            @Mapping(source = "numberAccount", target = "numberAccount"),
            @Mapping(source = "typeAccount", target = "typeAccount"),
            @Mapping(source = "balanceInitial", target = "balanceInitial"),
            @Mapping(source = "state", target = "state"),
            @Mapping(source = "balanceAvailable", target = "balanceAvailable"),
            @Mapping(source = "idClient", target = "idClient"),
            @Mapping(source = "client", target = "client"),

    })
    AccountDTO toAccount(AccountEntity accountEntity);
    List<AccountDTO> toAccounts(List<AccountEntity> accountEntities);



    @InheritInverseConfiguration
    @Mapping(target = "movements", ignore = true)
    AccountEntity toAccountEntity(AccountDTO accountDTO);

}
