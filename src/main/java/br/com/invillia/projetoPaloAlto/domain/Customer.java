package br.com.invillia.projetoPaloAlto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Customer {

    @Column(name = "dat_creation", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "dat_update")
    private LocalDateTime updatedAt;

    @Column(name = "des_name", nullable = false)
    private String name;
}
