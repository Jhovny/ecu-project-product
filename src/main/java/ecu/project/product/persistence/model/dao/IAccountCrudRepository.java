package ecu.project.product.persistence.model.dao;


import ecu.project.product.persistence.model.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IAccountCrudRepository extends CrudRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByNumberAccount(String numberAccount);
    List<AccountEntity> findByIdClient(long idClient);

}
