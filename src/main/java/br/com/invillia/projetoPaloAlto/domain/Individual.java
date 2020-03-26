package br.com.invillia.projetoPaloAlto.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Individual extends Customer{

    @Id
    @Column(name = "idt_individual")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "des_document", nullable = false, unique = true, length = 11)
    protected String document;

    @Column(name = "des_mother_name", nullable = false, unique = true, length = 9)
    protected String motherName;

    @Column(name = "des_nacional_document", nullable = false)
    protected String rg;

    @Column(name = "dat_birth", nullable = false)
    protected LocalDate birthDate;
}
