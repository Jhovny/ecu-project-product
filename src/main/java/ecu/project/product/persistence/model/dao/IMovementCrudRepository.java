package ecu.project.product.persistence.model.dao;

import ecu.project.product.persistence.model.entity.AccountEntity;
import ecu.project.product.persistence.model.entity.MovementEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IMovementCrudRepository extends CrudRepository<MovementEntity, Long> {

    List<MovementEntity> findByIdAccount(long idAccount);
    List<MovementEntity> findByDateBetweenAndIdAccount(Date dateStart, Date dataEnd, long idAccount);
}
