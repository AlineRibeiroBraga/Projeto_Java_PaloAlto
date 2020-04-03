package br.com.invillia.projetoPaloAlto.domain.dto;

import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import br.com.invillia.projetoPaloAlto.anotation.IsCNPJ;

@Data
@NoArgsConstructor
public class LegalEntityDTO extends CustomerDTO {

    @NotNull
    @IsCNPJ(message = "Invalided CNPJ")
    private String document;

    @NotNull
    private String tradeName;

    private List<IndividualDTO> individualsDTO;
}
