package br.com.invillia.projetoPaloAlto.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import br.com.invillia.projetoPaloAlto.anotation.IsZipCode;

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
