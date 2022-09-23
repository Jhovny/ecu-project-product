package ecu.project.product.domain.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovementResponse {
    private int codMessage;
    private String message;


}
