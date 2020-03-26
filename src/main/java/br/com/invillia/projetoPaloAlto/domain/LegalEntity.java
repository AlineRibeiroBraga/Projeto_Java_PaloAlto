package br.com.invillia.projetoPaloAlto.domain;

import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntity extends Customer{

    @Id
    @Column(name = "idt_legal_entity", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "des_document", nullable = false, unique = true, length = 14)
    protected String document;

    @Column(name = "des_trade_name", nullable = false)
    protected String tradeName;

    @ManyToMany
    @JoinTable(name="legal_entity_individual",
            joinColumns = @JoinColumn(name ="idt_legal_entity"),
            inverseJoinColumns = @JoinColumn(name = "idt_individual" )
    )
    protected List<Individual> individuals;

    @OneToMany
    @JoinColumn(name = "idt_address")
    protected List<Address> address;
}
