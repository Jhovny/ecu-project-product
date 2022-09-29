package ecu.project.product.domain.repository;


import ecu.project.product.domain.dto.MovementDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IMovementRepository {

    List<MovementDTO> getAll();
    List<MovementDTO> getByIdAccount(long idAccount);
    List<MovementDTO> getByDateBetweenAndIdAccount(LocalDateTime dateStart, LocalDateTime dateEnd, long idAccount);
    Optional<MovementDTO> getMovement(long id);
    MovementDTO save(MovementDTO movementDTO);
    void delete(long id);


}
