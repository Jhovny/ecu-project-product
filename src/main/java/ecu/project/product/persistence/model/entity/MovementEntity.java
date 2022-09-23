package ecu.project.product.persistence.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name="tb_movement")
public class MovementEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bi_id_movement")
    @Id
    private Long idMovement;

    @Column(name="ch_type_movement")
    private String typeMovement;

    @Column(name="db_value")
    private double value;

    @Column(name="db_balance")
    private double balance;

    @Column(name="dt_date")
    private LocalDateTime date;

    @Column(name="bi_id_account")
    private long idAccount;



    @ManyToOne
    @JoinColumn(name = "bi_id_account", insertable = false, updatable = false)
    private AccountEntity account;



}
