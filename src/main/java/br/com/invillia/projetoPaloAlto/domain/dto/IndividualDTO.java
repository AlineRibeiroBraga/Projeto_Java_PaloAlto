package br.com.invillia.projetoPaloAlto.domain.dto;

import java.util.List;

import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import lombok.Data;
import java.time.LocalDate;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import br.com.invillia.projetoPaloAlto.anotation.IsRG;
import br.com.invillia.projetoPaloAlto.anotation.IsCPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDTO extends CustomerDTO{

    @IsCPF(message = "Invalided CPF!")
    @NotNull
    private String document;

    @NotNull
    private String motherName;

    @IsRG(message = "Invalided RG!")
    @NotNull
    private String rg;

    @NotNull
    private LocalDate birthDate;

    private List<LegalEntityDTO> legalEntitiesDTO;
}
