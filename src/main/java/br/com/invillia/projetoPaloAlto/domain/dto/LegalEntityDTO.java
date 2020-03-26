package br.com.invillia.projetoPaloAlto.domain.dto;

import br.com.invillia.projetoPaloAlto.anotation.IsCNPJ;
import br.com.invillia.projetoPaloAlto.domain.Address;
import br.com.invillia.projetoPaloAlto.domain.Individual;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntityDTO extends CustomerDTO {

    @NotNull
    @IsCNPJ
    protected String document;

    @NotNull
    protected String tradeName;


    protected List<Individual> individuals;

    @NotNull
    protected List<Address> address;

    @Override
    public String toString() {
        return "LegalEntityDTO{" +
                "document='" + document + '\'' +
                ", tradeName='" + tradeName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
