package br.com.invillia.projetoPaloAlto.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CustomerDTO {

    @NotNull
    protected String name;
}
