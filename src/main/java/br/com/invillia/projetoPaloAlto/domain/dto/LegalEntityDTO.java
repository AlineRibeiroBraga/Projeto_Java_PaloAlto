package br.com.invillia.projetoPaloAlto.domain.dto;

import br.com.invillia.projetoPaloAlto.anotation.IsCNPJ;
import br.com.invillia.projetoPaloAlto.domain.model.Address;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
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
    private String document;

    @NotNull
    private String tradeName;

    private List<IndividualDTO> individualsDTO;

    @NotNull
    private List<AddressDTO> addressesDTO;

    @Override
    public String toString() {
        return "LegalEntityDTO{" +
                "document='" + document + '\'' +
                ", tradeName='" + tradeName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
