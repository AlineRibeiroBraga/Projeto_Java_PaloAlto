package br.com.invillia.projetoPaloAlto.domain.dto;

import java.util.List;
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
}
