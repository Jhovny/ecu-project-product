package ecu.project.product.domain.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MovementRequest {

    private String typeMovement; // RTRO // DPTO
    private double value;
    private long idAccount;


}
