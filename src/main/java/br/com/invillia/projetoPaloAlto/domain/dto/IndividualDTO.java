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
    protected String document;

    @NotNull
    protected String motherName;

    @IsRG
    @NotNull
    protected String rg;

    @NotNull
    protected LocalDate birthDate;

    @NotNull
    protected List<AddressDTO> address;
}
