package ecu.project.product.persistence.service;


import ecu.project.product.domain.dto.AccountDTO;
import ecu.project.product.domain.repository.IAccountRepository;
import ecu.project.product.persistence.mapper.IAccountMapper;
import ecu.project.product.persistence.model.dao.IAccountCrudRepository;
import ecu.project.product.persistence.model.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository implements IAccountRepository {

    @Autowired
    private IAccountCrudRepository iAccountCrudRepository;

    @Autowired
    private IAccountMapper iAccountMapper;

    @Override
    public List<AccountDTO> getAll() {

        return  iAccountMapper.toAccounts((List<AccountEntity>) iAccountCrudRepository.findAll());
    }

    @Override
    public List<AccountDTO> getByIdClient(long idClient){
        return  iAccountMapper.toAccounts((List<AccountEntity>) iAccountCrudRepository.findByIdClient(idClient));
    }


    @Override
    public Optional<AccountDTO> getAccount(long id) {
        return iAccountCrudRepository.findById(id).map(p -> iAccountMapper.toAccount(p));
    }

    @Override
    public Optional<AccountDTO> getByNumberAccount(String numberAccount) {
        return iAccountCrudRepository.findByNumberAccount(numberAccount).map(p -> iAccountMapper.toAccount(p));
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        return iAccountMapper.toAccount(iAccountCrudRepository.save( iAccountMapper.toAccountEntity(accountDTO)));

    }

    @Override
    public void delete(long id) {
        iAccountCrudRepository.deleteById(id);
    }

    @Override
    public void deleteByNumberAccount(String numberAccount) {
        Optional<AccountDTO> client0 = iAccountCrudRepository.findByNumberAccount(numberAccount).map(x -> iAccountMapper.toAccount(x));

        if (client0.isPresent()) {
            delete(client0.get().getIdAccount());
        }
    }
}
