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

//    @Column(name = "idt_legal_entity", nullable = false)
    @OneToMany(mappedBy = "legalEntity", cascade = CascadeType.ALL )
    protected List<Address> address;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result =1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegalEntity legalEntity = (LegalEntity) o;
        if(getId() == null){
            if(legalEntity.getId() != null)
                return false;
        }
        else if(!getId().equals(legalEntity.getId()))
            return false;
        return true;
    }
}
