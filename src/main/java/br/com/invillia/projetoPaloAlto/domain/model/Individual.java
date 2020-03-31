package br.com.invillia.projetoPaloAlto.domain.model;

import lombok.Data;
import java.util.List;
import java.time.LocalDate;
import javax.persistence.*;
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
    private Long id;

    @Column(name = "des_document", nullable = false, unique = true, length = 11)
    private String document;

    @Column(name = "des_mother_name", nullable = false)
    private String motherName;

    @Column(name = "des_nacional_document", nullable = false,  unique = true, length = 9)
    private String rg;

    @Column(name = "dat_birth", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL )
    private List<Address> addresses;
}
