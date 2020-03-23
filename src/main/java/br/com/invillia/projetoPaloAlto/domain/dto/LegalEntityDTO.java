package br.com.invillia.projetoPaloAlto.domain.dto;

import br.com.invillia.projetoPaloAlto.anotation.IsCNPJ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalEntityDTO extends CustomerDTO {

    @NotNull
    @IsCNPJ
    protected String document;

    @NotNull
    protected String tradeName;
}
