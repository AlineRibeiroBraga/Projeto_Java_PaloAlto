package br.com.invillia.projetoPaloAlto.domain.dto;

import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import br.com.invillia.projetoPaloAlto.anotation.IsCNPJ;

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

    @Override
    public String toString() {
        return "LegalEntityDTO{" +
                "document='" + document + '\'' +
                ", tradeName='" + tradeName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
