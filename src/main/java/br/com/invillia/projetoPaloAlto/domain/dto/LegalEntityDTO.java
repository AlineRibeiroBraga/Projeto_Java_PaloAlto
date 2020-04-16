package br.com.invillia.projetoPaloAlto.domain.dto;

import br.com.invillia.projetoPaloAlto.anotation.IsCNPJ;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

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
