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

//    @Column(name = "idt_individual", nullable = false)
    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL )
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
        Individual individual = (Individual) o;
        if(getId() == null){
            if(individual.getId() != null)
                return false;
        }
        else if(!getId().equals(individual.getId()))
            return false;
        return true;
    }
}
