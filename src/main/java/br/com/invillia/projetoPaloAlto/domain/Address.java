package br.com.invillia.projetoPaloAlto.domain;

import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name="idt_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "des_district", nullable = false)
    protected String district;

    @Column(name = "des_number", nullable = false)
    protected String number;

    @Column(name = "des_city", nullable = false)
    protected String city;

    @Column(name = "des_state", nullable = false)
    protected String state;

    @Column(name = "des_zip_code", nullable = false, length = 8)
    protected String zipCode;

    @Column(name = "flg_main", nullable = false)
    protected Boolean main;

    @ManyToOne
    @JoinColumn(name = "idt_legal_entity")
    protected LegalEntity legalEntity;

    @ManyToOne
    @JoinColumn(name = "idt_individual")
    protected Individual individual;
}