package ecu.project.product.domain.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class MovementDTO {
    private Long idMovement;
    private String typeMovement;
    private double value;
    private double balance;
    private LocalDateTime date;
    private long idAccount;
    private AccountDTO account;


}
