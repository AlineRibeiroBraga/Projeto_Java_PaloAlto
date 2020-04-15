package br.com.invillia.projetoPaloAlto.domain.dtoUpdate;

import br.com.invillia.projetoPaloAlto.domain.dto.AddressDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDTOUpdate {

    @NotNull
    protected String name;

    @NotNull
    protected List<AddressDTO> addressesDTO;
}
