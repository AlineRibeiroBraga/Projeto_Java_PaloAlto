package br.com.invillia.projetoPaloAlto.domain.dto;

import br.com.invillia.projetoPaloAlto.anotation.IsCPF;
import br.com.invillia.projetoPaloAlto.anotation.IsRG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDTO extends CustomerDTO{

    @IsCPF
    @NotNull
    private String document;

    @NotNull
    private String motherName;

    @IsRG
    @NotNull
    private String rg;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private List<AddressDTO> addressesDTO;
}
