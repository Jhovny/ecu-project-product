package ecu.project.product.domain.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClientDTO {

    private Long idClient;
    private String nombres;

}
