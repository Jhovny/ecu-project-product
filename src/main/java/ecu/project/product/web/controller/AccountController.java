package ecu.project.product.web.controller;



import ecu.project.product.domain.dto.AccountDTO;
import ecu.project.product.domain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAll() {
        return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAll(@PathVariable long id) {

        return accountService.getById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @GetMapping("/{idClient}/reportes")
    public ResponseEntity<List<AccountDTO>> getByIdClient(@PathVariable long idClient) {

        return new ResponseEntity<>(accountService.getByIdClient(idClient), HttpStatus.OK) ;


    }
    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO accountDTO){
        accountDTO.setBalanceAvailable(accountDTO.getBalanceInitial());
        return  new ResponseEntity<>(this.accountService.save(accountDTO), HttpStatus.OK) ;
    }


    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO accountDTO,@PathVariable Long id) {

        return accountService.getById(id)
                .map(x -> {
                    accountDTO.setIdAccount(x.getIdAccount());
                    return new ResponseEntity<>(accountService.save(accountDTO), HttpStatus.OK);})
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {

        if (accountService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
