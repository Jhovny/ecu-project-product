package ecu.project.product.persistence.service;


import ecu.project.product.domain.dto.MovementDTO;
import ecu.project.product.domain.repository.IMovementRepository;
import ecu.project.product.persistence.mapper.IMovementMapper;
import ecu.project.product.persistence.model.dao.IMovementCrudRepository;
import ecu.project.product.persistence.model.entity.MovementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MovementRepository implements IMovementRepository {

    @Autowired
    private IMovementCrudRepository iMovementCrudRepository;

    @Autowired
    private IMovementMapper iMovementMapper;

    @Override
    public List<MovementDTO> getAll() {
        return  iMovementMapper.toMovements((List<MovementEntity>) iMovementCrudRepository.findAll());

    }

    @Override
    public List<MovementDTO> getByIdAccount(long idAccount) {
        return  iMovementMapper.toMovements((List<MovementEntity>) iMovementCrudRepository.findByIdAccount(idAccount));

    }


    @Override
    public List<MovementDTO> getByDateBetweenAndIdAccount(LocalDateTime dateStart, LocalDateTime dateEnd, long idAccount){
        return  iMovementMapper.toMovements((List<MovementEntity>) iMovementCrudRepository.findByDateBetweenAndIdAccount(dateStart,dateEnd,  idAccount));

    }
    @Override
    public Optional<MovementDTO> getMovement(long id) {

        return iMovementCrudRepository.findById(id).map(c -> iMovementMapper.toMovement(c));
    }


    @Override
    public MovementDTO save(MovementDTO movementDTO) {
        return iMovementMapper.toMovement(iMovementCrudRepository.save( iMovementMapper.toMovementEntity(movementDTO)));

    }

    @Override
    public void delete(long id) {
        iMovementCrudRepository.deleteById(id);
    }


}
