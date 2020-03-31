package br.com.invillia.projetoPaloAlto.domain.dto;

import br.com.invillia.projetoPaloAlto.anotation.IsZipCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NotNull
    private String district;

    @NotNull
    private String number;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    @IsZipCode
    private String zipCode;

    @NotNull
    private Boolean main;

    private LegalEntityDTO legalEntityDTO;

    private IndividualDTO individualDTO;
}
