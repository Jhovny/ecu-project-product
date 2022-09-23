package ecu.project.product.web.controller;


import ecu.project.product.domain.dto.AccountDTO;
import ecu.project.product.domain.dto.MovementDTO;
import ecu.project.product.domain.dto.MovementRequest;
import ecu.project.product.domain.dto.MovementResponse;
import ecu.project.product.domain.service.AccountService;
import ecu.project.product.domain.service.MovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovementController {
    private static final Logger log = LoggerFactory.getLogger(MovementController.class);

    @Autowired
    private MovementService movementService;

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<MovementDTO>> getAll() {
        return new ResponseEntity<>(movementService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/account/{idAccount}")
    public ResponseEntity<List<MovementDTO>> getByIdAccount(@PathVariable long idAccount) {
        return new ResponseEntity<>(movementService.getByIdAccount(idAccount), HttpStatus.OK);
    }
    @GetMapping("/account/{idAccount}/dateini/{dateIni}/date/{dateFin}")
    public ResponseEntity<List<MovementDTO>> getByIdAccount(@PathVariable String dateIni,
                                                            @PathVariable String dateFin,
                                                            @PathVariable long idAccount) throws ParseException {

        return new ResponseEntity<>(
                movementService.getByDateBetweenAndIdAccount(
                        new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01"),
                        new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01") ,
                        idAccount),
                HttpStatus.OK);


    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDTO> getAll(@PathVariable long id) {

        return movementService.getById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<MovementResponse> create(@RequestBody MovementRequest request) {


        try {


            MovementResponse response = MovementResponse.builder().build();


            Optional<AccountDTO> accountDTO = accountService.getById(request.getIdAccount());

            if (accountDTO.isPresent()) {

                AccountDTO acountActual = accountDTO.get();

                if (request.getTypeMovement().equals("RTRO")) {

                    if (acountActual.getBalanceAvailable() > 0) {


                        if (acountActual.getBalanceAvailable() >= request.getValue()) {
                            double saldo = acountActual.getBalanceAvailable() - request.getValue();

                            MovementDTO movementDTO = MovementDTO.builder().build();
                            movementDTO.setTypeMovement(request.getTypeMovement());
                            movementDTO.setIdAccount(acountActual.getIdAccount());
                            movementDTO.setBalance(saldo);
                            movementDTO.setValue(-request.getValue());
                            movementDTO.setDate(LocalDateTime.now());

                            this.movementService.save(movementDTO);

                            acountActual.setBalanceAvailable(saldo);
                            this.accountService.save(acountActual);

                            response.setMessage("Transacción Autorizado ");

                        } else {
                            response.setCodMessage(-1);
                            response.setMessage("No dispone de saldo suficiente ");
                        }

                    } else {
                        response.setCodMessage(-2);
                        response.setMessage("No tiene saldo ");
                        // no hay saldo
                    }

                } else if (request.getTypeMovement().equals("DPTO")) {

                    double saldo = acountActual.getBalanceAvailable() + request.getValue();

                    MovementDTO movementDTO = MovementDTO.builder().build();
                    movementDTO.setTypeMovement(request.getTypeMovement());
                    movementDTO.setIdAccount(acountActual.getIdAccount());
                    movementDTO.setBalance(saldo);
                    movementDTO.setValue(request.getValue());
                    movementDTO.setDate(LocalDateTime.now());

                    this.movementService.save(movementDTO);

                    acountActual.setBalanceAvailable(saldo);
                    this.accountService.save(acountActual);

                    response.setMessage("Transacción Autorizado ");

                }


            } else {

                response.setCodMessage(-3);
                response.setMessage("No existe una cuenta ");
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception err) {
            log.error("Error movimiento", err);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<MovementDTO> update(@RequestBody MovementDTO movementDTO,@PathVariable long id) {

        return movementService.getById(id)
                .map(x -> {
                    movementDTO.setIdAccount(x.getIdMovement());
                  return  new ResponseEntity<>(movementService.save(movementDTO), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {

        if (movementService.delete(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
