package ecu.project.product.persistence.model.entity;

import io.micrometer.core.lang.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="tb_account")
public class AccountEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bi_id_account")
    @Id
    private long idAccount;
    @Column(name="cv_number_account")
    private String numberAccount;
    @Column(name="ch_type_account")
    private String typeAccount;
    @Column(name="db_balance_initial")
    private double balanceInitial;
    @Column(name="ch_state")
    private String state;
    @Column(name="bi_id_client")
    private long idClient;


    @Column(name="db_balance_available")
    private long balanceAvailable;


    @OneToMany(mappedBy = "account")
    private List<MovementEntity> movements;

    @ManyToOne
    @JoinColumn(name = "bi_id_client", insertable = false, updatable = false)
    private ClientEntity client;

}

