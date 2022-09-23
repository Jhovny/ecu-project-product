package ecu.project.product.domain.repository;


import ecu.project.product.domain.dto.AccountDTO;
import ecu.project.product.domain.dto.MovementDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IAccountRepository {

    List<AccountDTO> getAll();

    List<AccountDTO> getByIdClient(long idClient);
    Optional<AccountDTO> getAccount(long id);
    Optional<AccountDTO> getByNumberAccount(String numberAccount);

    AccountDTO save(AccountDTO accountDTO);
    void delete(long id);
    void deleteByNumberAccount(String numberAccount);

}
