package br.com.invillia.projetoPaloAlto.domain.dto;

import br.com.invillia.projetoPaloAlto.anotation.IsZipCode;
import br.com.invillia.projetoPaloAlto.domain.model.Individual;
import br.com.invillia.projetoPaloAlto.domain.model.LegalEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NotNull
    protected String district;

    @NotNull
    protected String number;

    @NotNull
    protected String city;

    @NotNull
    protected String state;

    @NotNull
    @IsZipCode
    protected String zipCode;

    @NotNull
    protected Boolean main;

    protected LegalEntity legalEntity;

    protected Individual individual;
}
