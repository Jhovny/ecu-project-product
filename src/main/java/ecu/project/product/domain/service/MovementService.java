package ecu.project.product.domain.service;



import ecu.project.product.domain.dto.MovementDTO;
import ecu.project.product.domain.repository.IMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovementService {
    @Autowired
    private IMovementRepository iMovementRepository;


    public List<MovementDTO> getAll() {
        return iMovementRepository.getAll();
    }
    public List<MovementDTO> getByIdAccount(Long idAccount) {
        return iMovementRepository.getByIdAccount(idAccount);
    }

    public List<MovementDTO> getByDateBetweenAndIdAccount(Date dateStart, Date dateEnd, Long idAccount) {
        return iMovementRepository.getByDateBetweenAndIdAccount(dateStart,dateEnd,idAccount);
    }


    public Optional<MovementDTO> getById(long id) {
        return iMovementRepository.getMovement(id);
    }
    public MovementDTO save (MovementDTO movementDTO){

       return iMovementRepository.save(movementDTO);

    }

    public boolean delete (long id){
        return getById(id).map(product -> {
            iMovementRepository.delete(id);
            return true;
        }).orElse(false);

    }
}
