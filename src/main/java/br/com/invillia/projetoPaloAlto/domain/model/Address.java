package br.com.invillia.projetoPaloAlto.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name="idt_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "des_district", nullable = false)
    private String district;

    @Column(name = "des_number", nullable = false)
    private String number;

    @Column(name = "des_city", nullable = false)
    private String city;

    @Column(name = "des_state", nullable = false)
    private String state;

    @Column(name = "des_zip_code", nullable = false, length = 8)
    private String zipCode;

    @Column(name = "flg_main", nullable = false)
    private Boolean main;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idt_legal_entity", referencedColumnName = "idt_legal_entity")
    private LegalEntity legalEntity;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "idt_individual", referencedColumnName = "idt_individual")
    private  Individual individual;
}