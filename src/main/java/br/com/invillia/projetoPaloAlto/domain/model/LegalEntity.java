package br.com.invillia.projetoPaloAlto.domain.model;

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
    private Long id;

    @Column(name = "des_document", nullable = false, unique = true, length = 14)
    private String document;

    @Column(name = "des_trade_name", nullable = false)
    private String tradeName;

    @ManyToMany
    @JoinTable(name="legal_entity_individual",
            joinColumns = @JoinColumn(name ="idt_legal_entity"),
            inverseJoinColumns = @JoinColumn(name = "idt_individual" )
    )
    private List<Individual> individuals;

    @OneToMany(mappedBy = "legalEntity", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
