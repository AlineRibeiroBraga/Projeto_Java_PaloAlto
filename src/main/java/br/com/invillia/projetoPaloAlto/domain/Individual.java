package br.com.invillia.projetoPaloAlto.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Individual extends Customer{

    @Id
    @Column(name = "idt_individual")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "des_document", nullable = false, unique = true, length = 11)
    private String document;

    @Column(name = "des_mother_name", nullable = false, unique = true, length = 9)
    private String motherName;

    @Column(name = "des_nacional_document", nullable = false)
    private String rg;

    @Column(name = "dat_birth", nullable = false)
    private LocalDate birthDate;

    @ManyToMany
    @Column(name = "idt_legal_entity")
    @JoinTable(name="legal_entity_individual",
            joinColumns = @JoinColumn(name = "idt_individual"),
            inverseJoinColumns = @JoinColumn(name = "idt_legal_entity")
    )
    private List<LegalEntity> legalEntity;
}
