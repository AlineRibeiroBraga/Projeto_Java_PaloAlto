package br.com.invillia.projetoPaloAlto.mapper;

import br.com.invillia.projetoPaloAlto.domain.LegalEntity;
import br.com.invillia.projetoPaloAlto.domain.dto.LegalEntityDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LegalEntityMapper {

    public LegalEntity legalEntityDTOTolegalEntity(LegalEntityDTO legalEntityDTO) {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setName(legalEntityDTO.getName());
        legalEntity.setDocument(legalEntityDTO.getDocument());
        legalEntity.setTradeName(legalEntityDTO.getTradeName());
        legalEntity.setCreatedAt(LocalDateTime.now());

        return legalEntity;
    }
}
