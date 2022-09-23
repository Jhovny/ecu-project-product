package ecu.project.product.persistence.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="tb_client")
public class ClientEntity {


    @Column(name="bi_id_client")
    @Id
    private long idClient;

    @Column(name="cv_nombres")
    private String nombres;

    @OneToMany(mappedBy = "client")
    private List<AccountEntity> accounts;
}

