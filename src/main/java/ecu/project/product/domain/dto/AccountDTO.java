package ecu.project.product.domain.dto;


import io.micrometer.core.lang.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountDTO {
    private long idAccount;
    private String numberAccount;
    private String typeAccount;
    private double balanceInitial;
    private String state;
    private long idClient;
    private double balanceAvailable;
    private ClientDTO client;
}
