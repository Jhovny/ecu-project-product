package ecu.project.product.domain.service;


import ecu.project.product.domain.dto.AccountDTO;
import ecu.project.product.domain.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    public List<AccountDTO> getAll() {
        return iAccountRepository.getAll();
    }

    public List<AccountDTO> getByIdClient(Long idClient) {
        return iAccountRepository.getByIdClient(idClient);
    }
    public Optional<AccountDTO> getById(long id) {
        return iAccountRepository.getAccount(id);
    }

    public void getByNumberAccount(String numberAccount) {
        iAccountRepository.getByNumberAccount(numberAccount);
    }


    public AccountDTO save (AccountDTO accountDTO){
       return iAccountRepository.save(accountDTO);
    }
    public boolean delete (long id){
        return getById(id).map(product -> {
            iAccountRepository.delete(id);
            return true;
        }).orElse(false);

    }
}
