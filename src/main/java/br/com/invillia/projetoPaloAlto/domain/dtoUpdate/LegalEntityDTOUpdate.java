package br.com.invillia.projetoPaloAlto.domain.dtoUpdate;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LegalEntityDTOUpdate extends CustomerDTOUpdate{

    @NotNull
    private String tradeName;

    private List<IndividualDTOUpdate> individualsDTOUpdate;
}
