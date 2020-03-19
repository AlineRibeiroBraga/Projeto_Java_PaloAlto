package br.com.invillia.projetoPaloAlto.domain;

import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntity extends Customer{

    @Id
    @Column(name = "idt_legal_entity", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "des_document", nullable = false, unique = true, length = 14)
    private String document;

    @Column(name = "des_trade_name", nullable = false)
    private String tradeName;
}
