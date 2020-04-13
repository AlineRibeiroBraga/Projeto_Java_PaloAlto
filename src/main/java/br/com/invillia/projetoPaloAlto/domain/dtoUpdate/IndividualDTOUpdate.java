package br.com.invillia.projetoPaloAlto.domain.dtoUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class IndividualDTOUpdate extends CustomerDTOUpdate{

    @NotNull
    private String motherName;
}
