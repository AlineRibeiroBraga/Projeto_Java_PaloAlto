package br.com.invillia.projetoPaloAlto.domain.model;

import lombok.Data;
import java.time.LocalDateTime;
import javax.persistence.Column;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

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
}
