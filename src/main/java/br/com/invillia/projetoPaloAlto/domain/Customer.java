package br.com.invillia.projetoPaloAlto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Customer {

    @CreationTimestamp
    @Column(name = "dat_creation", nullable = false, columnDefinition = "TIMESTAMP")
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "dat_update")
    protected LocalDateTime updatedAt;

    @Column(name = "des_name", nullable = false)
    protected String name;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "idt_address")
    protected List<Address> address;
}
